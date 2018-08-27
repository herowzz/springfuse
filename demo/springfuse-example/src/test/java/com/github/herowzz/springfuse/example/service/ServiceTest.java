package com.github.herowzz.springfuse.example.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.herowzz.springfuse.example.domain.account.User;
import com.github.herowzz.springfuse.example.service.account.UserService;

@RunWith(SpringRunner.class)
@Configuration
@SpringBootTest
@Transactional
public class ServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void testR1() {
		List<User> userList = userService.findAll();
		assertThat(userList.size()).isGreaterThan(0);
	}

}
