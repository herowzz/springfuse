package com.github.herowzz.springfuse.security.manager;

import com.github.herowzz.springfuse.security.model.TokenModel;

public interface ITokenManager {

	TokenModel createToken(String uid);

	String getUidByToken(String token);

}
