package com.github.herowzz.springfuse.security.manager;

import com.github.herowzz.springfuse.security.manager.impl.MemoryTokenManager;
import com.github.herowzz.springfuse.security.model.TokenEnum;

public class TokenFactory {

	private int expireSeconds = 60 * 60 * 24;
	private boolean isMultiLogin = true;
	private TokenEnum type = TokenEnum.Memory;

	public TokenFactory() {
	}

	/**
	 * 实例化TokenFactory
	 * @param expireSeconds 过期时间
	 * @param isMultiLogin 是否支持同一用户多终端登陆
	 * @param type 存储类型
	 */
	public TokenFactory(int expireSeconds, boolean isMultiLogin, TokenEnum type) {
		this.expireSeconds = expireSeconds;
		this.isMultiLogin = isMultiLogin;
		this.type = type;
	}

	public ITokenManager createTokenManager() {
		switch (this.type) {
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
