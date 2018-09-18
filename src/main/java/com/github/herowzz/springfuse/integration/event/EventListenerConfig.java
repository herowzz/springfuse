package com.github.herowzz.springfuse.integration.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 事件监听注册配置
 * @author wangzz
 */
@Configuration
public class EventListenerConfig {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Bean
	public IEventService EventService() {
		return new SpringEventService(applicationEventPublisher);
	}

}
