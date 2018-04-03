package com.github.herowzz.springfuse.example.service;

import org.springframework.stereotype.Service;

import com.github.herowzz.springfuse.security.ITokenService;

@Service
public class MemoryTokenService implements ITokenService {
	
//	Cache<Integer, String> cache = CacheBuilder.newBuilder().initialCapacity(10);

	@Override
	public void putToken(String userId, String accessToken, String refreshToken) {
		putAccessToken(userId, accessToken);
		putRefrechToken(userId, refreshToken);
	}

	private void putRefrechToken(String userId, String refreshToken) {
		// TODO Auto-generated method stub
	}

	private void putAccessToken(String userId, String accessToken) {
		// TODO Auto-generated method stub
	}

}
