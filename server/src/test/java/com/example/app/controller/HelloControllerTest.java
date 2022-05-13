// package com.example.app.controller;

// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import
// org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

// import static org.hamcrest.Matchers.containsString;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

// @AutoConfigureMockMvc
// // @SpringBootTest(classes = DbMvcTestApplication.class)
// public class HelloControllerTest {

// // mockMvc TomcatサーバへデプロイすることなくHttpリクエスト・レスポンスを扱うためのMockオブジェクト
// @Autowired
// private MockMvc mockMvc;

// // getリクエストでviewを指定し、httpステータスでリクエストの成否を判定
// @Test
// void init処理が走って200が返る() throws Exception {
// // // andDo(print())でリクエスト・レスポンスを表示
// // this.mockMvc.perform(get("/hello/init")).andDo(print())
// // .andExpect(status().isOk());
// }
// }
