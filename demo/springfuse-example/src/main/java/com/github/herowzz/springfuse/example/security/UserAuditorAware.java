package com.github.herowzz.springfuse.example.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.github.herowzz.springfuse.data.domain.BaseUser;
import com.github.herowzz.springfuse.security.manager.UserSessionManager;

@Component
public class UserAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		String userName = "";
		BaseUser user = UserSessionManager.getUser();
		if (user != null) {
			userName = user.getRealname();
		}
		return Optional.of(userName);
	}

}