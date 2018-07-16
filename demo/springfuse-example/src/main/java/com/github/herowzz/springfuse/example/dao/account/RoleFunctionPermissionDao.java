package com.github.herowzz.springfuse.example.dao.account;

import java.util.List;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.example.domain.account.RoleFunctionPermission;

public interface RoleFunctionPermissionDao extends IBaseDao<RoleFunctionPermission, String> {

	List<RoleFunctionPermission> findByRoleId(String roleId);

}
