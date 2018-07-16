package com.github.herowzz.springfuse.example.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.data.jpa.service.BaseService;
import com.github.herowzz.springfuse.example.dao.account.RoleDao;
import com.github.herowzz.springfuse.example.domain.account.Role;

@Service
public class RoleService extends BaseService<Role, String> {

	@Autowired
	private RoleDao roleDao;

	@Override
	protected IBaseDao<Role, String> getEntityDao() {
		return roleDao;
	}

}
