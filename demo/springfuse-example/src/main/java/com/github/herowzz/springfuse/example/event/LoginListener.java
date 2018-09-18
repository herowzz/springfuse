package com.github.herowzz.springfuse.example.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.github.herowzz.springfuse.example.domain.account.User;

@Component
public class LoginListener {

	@EventListener
	public void loginEvent(User user) {
		System.out.println("LoginListener.loginEvent() receive event...");
		System.out.println(user.getId() + " - " + user.getUsername());
	}

}
