package com.github.herowzz.springfuse.example.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.data.jpa.service.BaseService;
import com.github.herowzz.springfuse.example.dao.UserDao;
import com.github.herowzz.springfuse.example.domain.User;

@Service
public class UserService extends BaseService<User, String> {

	@Autowired
	private UserDao userDao;

	@Override
	protected IBaseDao<User, String> getEntityDao() {
		return userDao;
	}

	/**
	 * 根据用户登录名密码查询用户对象
	 * @param username 登录名
	 * @param password 登录密码
	 * @return 用户对象.不存在返回Null
	 */
	public User findByUsernameAndPassword(String username, String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}

	/**
	 * 登录逻辑<br>
	 * 记录登录IP和登录时间
	 * @param user 登录用户
	 * @param ip 访问IP地址
	 * @return 处理后的用户对象
	 */
	public User login(User user, String ip) {
		user.setLastLoginIp(ip);
		user.setLastLoginTime(LocalDateTime.now());
		user = userDao.save(user);
		return user;
	}

}
