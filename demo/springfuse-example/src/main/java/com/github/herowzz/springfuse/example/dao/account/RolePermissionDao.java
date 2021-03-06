package com.github.herowzz.springfuse.example.dao.account;

import java.util.List;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.example.domain.account.RolePermission;

public interface RolePermissionDao extends IBaseDao<RolePermission, String> {

	public List<RolePermission> findByRoleId(String roleId);

}
