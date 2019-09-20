package com.github.herowzz.springfuse.api.handler;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import com.github.herowzz.springfuse.security.manager.UserSessionManager;

@WebListener
public class RequestListenter implements ServletRequestListener {

	@Override
	public void requestInitialized(ServletRequestEvent servletRequestEvent) {
	}

	@Override
	public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
		UserSessionManager.removeUser();
	}

}
