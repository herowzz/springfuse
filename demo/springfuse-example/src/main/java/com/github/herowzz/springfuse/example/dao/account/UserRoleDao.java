package com.github.herowzz.springfuse.example.dao.account;

import java.util.List;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.example.domain.account.UserRole;

public interface UserRoleDao extends IBaseDao<UserRole, String> {

	List<UserRole> findByUserId(String userId);

}
