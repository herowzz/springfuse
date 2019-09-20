package com.github.herowzz.springfuse.security.manager;

import com.github.herowzz.springfuse.data.domain.BaseUser;

public abstract class UserSessionManager {

	private static ThreadLocal<BaseUser> USER_LOCAL = new ThreadLocal<>();

	/**
	 * 把当前用户放入当前线程session中
	 * @param <T> Extends User Class Type
	 * @param user 用户对象
	 */
	public static <T extends BaseUser> void setUser(T user) {
		USER_LOCAL.set(user);
	}

	/**
	 * 从当前线程session中获取用户
	 * @return 用户对象
	 */
	public static BaseUser getUser() {
		return USER_LOCAL.get();
	}

	/**
	 * 从当前线程session中移除用户信息
	 */
	public static void removeUser() {
		USER_LOCAL.remove();
	}

}
