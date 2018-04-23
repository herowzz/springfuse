package com.github.herowzz.springfuse.security.manager;

import com.github.herowzz.springfuse.security.manager.impl.MemoryTokenManager;
import com.github.herowzz.springfuse.security.model.TokenEnum;

public class TokenFactory {

	private int expireSeconds = 60 * 60 * 24;
	private boolean isMultiLogin = true;
	private TokenEnum type = TokenEnum.Memory;

	public TokenFactory() {
	}

	public TokenFactory(int expireSeconds, String type) {
		this.expireSeconds = expireSeconds;
		this.type = TokenEnum.getByValue(type);
	}

	public TokenFactory(boolean isMultiLogin, String type) {
		this.isMultiLogin = isMultiLogin;
		this.type = TokenEnum.getByValue(type);
	}

	public TokenFactory(int expireSeconds, boolean isMultiLogin) {
		this.expireSeconds = expireSeconds;
		this.isMultiLogin = isMultiLogin;
	}

	public TokenFactory(int expireSeconds, boolean isMultiLogin, String type) {
		this.expireSeconds = expireSeconds;
		this.isMultiLogin = isMultiLogin;
		this.type = TokenEnum.getByValue(type);
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
