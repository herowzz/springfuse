package com.github.herowzz.springfuse.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;

import com.github.herowzz.springfuse.api.log.LogAspect;
import com.github.herowzz.springfuse.data.jpa.config.JpaListenerConfig;
import com.github.herowzz.springfuse.example.service.system.LogInfoService;
import com.github.herowzz.springfuse.integration.event.EventListenerConfig;
import com.github.herowzz.springfuse.security.manager.ITokenManager;
import com.github.herowzz.springfuse.security.manager.TokenFactory;
import com.github.herowzz.springfuse.security.model.TokenTypeEnum;

@Configuration
@Import({ JpaListenerConfig.class, EventListenerConfig.class })
@EnableAsync
@EnableCaching
@EnableSpringDataWebSupport
public class ExampleConfig {

	@Autowired
	private LogInfoService logInfoService;

	@Bean
	public LogAspect LogAspect() {
		LogAspect acpect = new LogAspect();
		acpect.setLogManage(logInfoService);
		return acpect;
	}

	@Bean
	public ITokenManager TokenManager() {
		TokenFactory tokenFactory = new TokenFactory(3600, false);
		return tokenFactory.createTokenManager(TokenTypeEnum.Memory);
	}

}
