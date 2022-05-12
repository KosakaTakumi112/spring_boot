package com.example.app.controller;

import javax.transaction.Transactional;

import com.example.app.entity.User;
import com.example.app.repository.UserRepository;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Transactional // メソッドの実行のたびにロールバックする
@Import(TestConfiguration.class)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    // @AfterEach
    // void tearDown() {
    // userRepository.getEm().flush();
    // }

    @Test
    @DatabaseSetup(value = "/testData/") // 初期状態のDB
    @ExpectedDatabase(value = "/users/create", assertionMode = DatabaseAssertionMode.NON_STRICT)
    void createメソッドでユーザが新しく生成される() {
        User user = new User();
        user.setName("test4");
        user.setEmail("test4@email.com");
        user.setInquiry("テスト4です。");

        userRepository.save(user);
    }

}
