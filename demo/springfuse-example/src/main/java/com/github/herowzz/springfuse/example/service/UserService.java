package com.github.herowzz.springfuse.example.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.data.jpa.service.BaseService;
import com.github.herowzz.springfuse.example.dao.UserDao;
import com.github.herowzz.springfuse.example.domain.User;

@Service
public class UserService extends BaseService<User, Long> {

	@Autowired
	private UserDao userDao;

	@Override
	protected IBaseDao<User, Long> getEntityDao() {
		return userDao;
	}

	public User login(String username, String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}

	public User updateLoginInfo(User user, String ip) {
		user.setLastLoginIp(ip);
		user.setLastLoginTime(LocalDateTime.now());
		return userDao.save(user);
	}

}
