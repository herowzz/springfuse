package com.github.herowzz.springfuse.security.manager;

import com.github.herowzz.springfuse.security.model.TokenModel;

/**
 * Token操作管理
 * @author wangzz
 */
public interface ITokenManager {

	/**
	 * 创建一个token关联上指定用户
	 * @param userId 指定用户的id
	 * @return 生成的token
	 */
	TokenModel createToken(String uid);

	/**
	 * 根据token获取用户Id
	 * @param token token字符
	 * @return 用户Id
	 */
	String getUidByToken(String token);

}
