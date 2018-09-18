package com.github.herowzz.springfuse.integration.event;

public interface IEventService {

	/**
	 * 发布通知
	 * @param obj 通知对象
	 */
	public void post(Object obj);

}
