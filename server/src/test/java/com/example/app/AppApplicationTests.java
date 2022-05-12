package com.example.app;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppApplicationTests {

	TestRestTemplate restTemplate = new TestRestTemplate();

	@LocalServerPort
	int port;

	@Test
	public void testHello() {
	assertThat(restTemplate.getForObject("http://localhost:" + port,
	String.class), is("Hello World!"));
	}

}
