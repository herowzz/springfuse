package com.github.herowzz.springfuse.example.util;

public abstract class SecurityConstant {

	/**
	 * 私钥
	 */
	public static String SECRET_KEY = "Jdssg_Asfv+_SDsdf.sdsfg:s':>/Hf&TG&%duas";

	/**
	 * 发行商
	 */
	public static String ISS_USER = "springfuse";

	/**
	 * accessToken过期时间(分钟)
	 */
	public static long ACCESS_TOKEN_EXPIRE = 1 * 24 * 60;

	/**
	 * refreshToken过期时间(分钟)
	 */
	public static long REFRESH_TOKEN_EXPIRE = 7 * 24 * 60;

	/**
	 * checKCode过期时间(分钟)
	 */
	public static long CHECK_CODE_EXPIRE = 10;

}
