package com.github.herowzz.springfuse.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.github.herowzz.springfuse.security.manager.ITokenManager;
import com.github.herowzz.springfuse.security.manager.TokenFactory;
import com.github.herowzz.springfuse.security.model.TokenEnum;

@Configuration
@EnableSpringDataWebSupport
public class ExampleConfig {

	@Bean
	public ITokenManager TokenManager() {
		TokenFactory tokenFactory = new TokenFactory(3600, false);
		return tokenFactory.createTokenManager(TokenEnum.Memory);
	}

}
