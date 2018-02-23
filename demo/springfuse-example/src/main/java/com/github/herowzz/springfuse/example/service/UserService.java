package com.github.herowzz.springfuse.example.service;

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

}
