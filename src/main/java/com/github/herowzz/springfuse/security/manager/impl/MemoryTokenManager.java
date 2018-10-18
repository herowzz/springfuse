package com.github.herowzz.springfuse.security.manager.impl;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.github.herowzz.springfuse.core.util.DateUtils;
import com.github.herowzz.springfuse.security.manager.ITokenManager;
import com.github.herowzz.springfuse.security.model.TokenModel;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class MemoryTokenManager implements ITokenManager {

	private Cache<String, String> tokenIdCache;
	private Cache<String, String> idTokenCache;
	private int expireSeconds = 60 * 60 * 24;
	private boolean isMultiLogin = true;

	public MemoryTokenManager(int expireSeconds, boolean isMultiLogin) {
		this.tokenIdCache = CacheBuilder.newBuilder().expireAfterWrite(expireSeconds, TimeUnit.SECONDS).build();
		this.idTokenCache = CacheBuilder.newBuilder().expireAfterWrite(expireSeconds, TimeUnit.SECONDS).build();
		this.expireSeconds = expireSeconds;
		this.isMultiLogin = isMultiLogin;
	}

	@Override
	public TokenModel createToken(String uid) {
		LocalDateTime expireTime = LocalDateTime.now().plusSeconds(expireSeconds);
		long expireTimeLong = DateUtils.localDateTimeToDate(expireTime).getTime();
		String token = UUID.randomUUID().toString().replace("-", "");
		TokenModel model = new TokenModel(uid, token, expireTimeLong);
		if (!isMultiLogin) {
			String oldToken = idTokenCache.getIfPresent(uid);
			if (oldToken != null) {
				tokenIdCache.invalidate(oldToken);
				idTokenCache.invalidate(uid);
			}
		}
		tokenIdCache.put(token, uid);
		idTokenCache.put(uid, token);
		return model;
	}

	@Override
	public String getUidByToken(String token) {
		return tokenIdCache.getIfPresent(token);
	}

	@Override
	public void removeToken(String token) {
		tokenIdCache.invalidate(token);
	}

}
