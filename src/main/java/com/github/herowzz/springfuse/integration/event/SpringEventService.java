package com.github.herowzz.springfuse.integration.event;

import org.springframework.context.ApplicationEventPublisher;

public class SpringEventService implements IEventService {

	private ApplicationEventPublisher publisher;

	public SpringEventService(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	public void post(Object obj) {
		publisher.publishEvent(obj);
	}

}
