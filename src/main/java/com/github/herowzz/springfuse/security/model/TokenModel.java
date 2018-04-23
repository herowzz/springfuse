package com.github.herowzz.springfuse.security.model;

public class TokenModel {

	private String userId;

	private String token;

	private Long expireTime;

	public TokenModel(String userId, String token, Long expireTime) {
		this.userId = userId;
		this.token = token;
		this.expireTime = expireTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
}
