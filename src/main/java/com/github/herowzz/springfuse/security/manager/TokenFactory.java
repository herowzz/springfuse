package com.github.herowzz.springfuse.security.manager;

import com.github.herowzz.springfuse.security.manager.impl.MemoryTokenManager;
import com.github.herowzz.springfuse.security.model.TokenTypeEnum;

public class TokenFactory {

	private int expireSeconds = 60 * 60 * 24;
	private boolean isMultiLogin = true;

	public TokenFactory() {
	}

	/**
	 * 实例化TokenFactory
	 * @param expireSeconds 过期时间(单位:秒)
	 * @param isMultiLogin 是否支持同一用户多终端登陆
	 */
	public TokenFactory(int expireSeconds, boolean isMultiLogin) {
		this.expireSeconds = expireSeconds;
		this.isMultiLogin = isMultiLogin;
	}

	public ITokenManager createTokenManager(TokenTypeEnum type) {
		switch (type) {
		case Memory:
			return new MemoryTokenManager(expireSeconds, isMultiLogin);
		case Redis:
			break;
		default:
			break;
		}
		return new MemoryTokenManager(expireSeconds, isMultiLogin);
	}

}
