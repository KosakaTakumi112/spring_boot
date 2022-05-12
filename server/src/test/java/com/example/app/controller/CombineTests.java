package com.example.app.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.web.reactive.function.BodyInserters.*;

import static org.hamcrest.MatcherAssert.assertThat;

import com.example.app.entity.User;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "50000")
public class CombineTests {

  @Autowired
  private WebTestClient webClient;

  @Test
  void 結合テスト() {
    this.webClient.get().uri("/users/new").exchange().expectStatus().isOk();

    // 入れるデータ
    String name = "combinetest3";
    String email = "combinetest3@email.com";
    String inquiry = "結合テスト3です。";

    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setInquiry(inquiry);

    this.webClient.post()
        .uri("/users/confirm")
        // .body(BodyInserters.fromMultipartData("User", user))
        .body(fromFormData("name", name).with("email", email).with("inquiry", inquiry))
        .exchange()
        .expectStatus()
        .isOk();

    // .bodyToMono(Void.class);
    assertThat(user.getName(), is(name));
    assertThat(user.getEmail(), is(email));
    assertThat(user.getInquiry(), is(inquiry));

  }
}