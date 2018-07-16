package com.github.herowzz.springfuse.example.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.data.jpa.service.BaseService;
import com.github.herowzz.springfuse.example.dao.account.UserDao;
import com.github.herowzz.springfuse.example.domain.account.User;

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

}
