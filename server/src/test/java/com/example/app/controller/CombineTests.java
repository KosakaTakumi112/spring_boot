package com.example.app.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.springframework.web.reactive.function.BodyInserters.*;

import java.util.List;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import com.example.app.CsvDataSetLoader;
import com.example.app.entity.User;
import com.example.app.service.UserService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class, // DIを利用できるようにする
    TransactionDbUnitTestExecutionListener.class, // DB操作の際のトランザクションを設定する場合は追加する
    DbUnitTestExecutionListener.class
})
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "50000")
@AutoConfigureMockMvc
@Transactional
@Import(TestConfiguration.class)
@DatabaseSetup(value = "/combineTestData/") // 初期状態のDB

public class CombineTests {
  @Autowired
  private UserService userService;

  // webクライアント用意
  @Autowired
  private WebTestClient webClient;

  @Test
  void 結合テスト() {
    // 既存DBテーブルからレコードを取得（CSVファイルから登録済みの2件取得）
    List<User> userListBefore = userService.findAll();
    assertThat(userListBefore.size(), is(2));

    // newルート確認
    this.webClient.get().uri("/users/new").exchange().expectStatus().isOk();

    // データ入力
    String name = "combinetest3";
    String email = "combinetest3@email.com";
    String inquiry = "結合テスト3です。";

    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setInquiry(inquiry);

    // 確認画面へのルート確認＋データ受け渡し
    this.webClient.post()
        .uri("/users/confirm")
        .body(fromFormData("name", name).with("email", email).with("inquiry", inquiry))
        .exchange()
        .expectStatus()
        .isOk();

    assertThat(user.getName(), is(name));
    assertThat(user.getEmail(), is(email));
    assertThat(user.getInquiry(), is(inquiry));

    // DBへの保存処理+完了画面へのルート確認
    this.webClient.post()
        .uri("/users")
        .body(fromFormData("name", name).with("email", email).with("inquiry", inquiry))
        .exchange()
        .expectStatus()
        .isOk();

    // 登録後のDBの取得(件数が当初より1件増加)
    List<User> userListAfter = userService.findAll();
    assertThat(userListAfter.size(), is(3));
  }
}