package com.github.herowzz.springfuse.example.config;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.context.annotation.Configuration;

import com.github.herowzz.springfuse.data.jpa.interceptor.SoftDeleteEventListener;

/**
 * 实体监听器注册配置
 * @author wangzz
 */
@Configuration
public class JpaListenerConfig {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@PostConstruct
	protected void init() {
		final SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
		final EventListenerRegistry eventListenerRegistry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
		eventListenerRegistry.getEventListenerGroup(EventType.DELETE).appendListener(new SoftDeleteEventListener());
	}

}
