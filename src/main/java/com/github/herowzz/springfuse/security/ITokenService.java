package com.github.herowzz.springfuse.security;

public interface ITokenService {

	public void putToken(String userId, String accessToken, String refreshToken);

}
