package com.example.app;

import java.util.List;

import javax.transaction.Transactional;

import com.example.app.entity.User;
import com.example.app.service.UserService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
@SpringBootTest
@DatabaseSetup(value = "/testData/") // 初期状態のDB
@Transactional // メソッドの実行のたびにロールバックする
@Import(TestConfiguration.class)
public class DBSelectTest {
  @Autowired
  private UserService userService;

  @Test
  public void dbから正常にデータを取得できるか() throws Exception {
    List<User> userList = userService.findAll();

    
    // Daoで正常にテーブルからレコードを取得できたか
    assertThat(userList.size(), is(2));
  }
}