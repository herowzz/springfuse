package com.github.herowzz.springfuse.example.service.account;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.data.jpa.service.BaseService;
import com.github.herowzz.springfuse.example.dao.account.RoleDao;
import com.github.herowzz.springfuse.example.dao.account.UserRoleDao;
import com.github.herowzz.springfuse.example.domain.account.Role;
import com.github.herowzz.springfuse.example.domain.account.User;
import com.github.herowzz.springfuse.example.domain.account.UserRole;

@Service
public class RoleService extends BaseService<Role, String> {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	protected IBaseDao<Role, String> getEntityDao() {
		return roleDao;
	}

	/**
	 * 获取该用户的角色列表
	 * @param userId 用户id
	 * @return 角色List
	 */
	public List<Role> findRoleListByUser(String userId) {
		return userRoleDao.findByUserId(userId).stream().map(r -> {
			Role role = r.getRole();
			role.getName();
			return r.getRole();
		}).collect(Collectors.toList());
	}

	public UserRole addRole(User user, Role role) {
		return userRoleDao.save(new UserRole(user, role));
	}

}
