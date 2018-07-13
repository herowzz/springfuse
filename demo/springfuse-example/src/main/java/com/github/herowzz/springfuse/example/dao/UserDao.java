package com.github.herowzz.springfuse.example.dao;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.example.domain.account.User;

public interface UserDao extends IBaseDao<User, String> {

	User findByUsernameAndPassword(String username, String password);

}
