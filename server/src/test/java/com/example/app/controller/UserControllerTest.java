package com.example.app.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc // MockMvcの利用
@SpringBootTest
public class UserControllerTest {
  private static final Object User = null;
  @Autowired
  private MockMvc mockmvc;

  // テスト①
  @Test
  void お問合せ作成画面が問題なく表示される() throws Exception {
    this.mockmvc.perform(get("/users/new"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("users/new"));
  }

  @Test
  void お問合せ一覧画面が問題なく表示される() throws Exception {
    this.mockmvc.perform(get("/users"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("users/index"));

  }

  @Test
  void 新規お問合せ作成後確認画面が表示される() throws Exception {
    this.mockmvc.perform(post("/users/confirm")
        .param("name", "a")
        .param("email", "aa")
        .param("inquiry", "aaa"))
        .andExpect(status().isOk())
        .andExpect(model().attribute("User", User))
        .andExpect(view().name("users/confirm"));
  }

  @Test
  void 確認画面からお問合せ作成後完了画面が表示される() throws Exception {

    MockHttpServletRequestBuilder createMessage = post("/users")
        .param("name", "a")
        .param("email", "aa")
        .param("inquiry", "aaa");

    this.mockmvc.perform(createMessage)
        .andExpect(status().isOk())
        .andExpect(model().attribute("User", User))
        .andExpect(view().name("users/complete"));
    // .andExpect(model().attribute("email", "aa"))
    // .andExpect(model().attribute("inquiry", "aaa"));
  }

  // @Test
  // void getUsers処理でModelのmessageにhelloが渡される() throws Exception {
  // this.mockmvc.perform(get("/users/list"))
  // .andExpect(model().attribute("message", "hello"));
  // }

}
