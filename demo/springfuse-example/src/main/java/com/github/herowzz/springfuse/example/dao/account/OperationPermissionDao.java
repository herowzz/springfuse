package com.github.herowzz.springfuse.example.dao.account;

import java.util.List;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.example.domain.account.OperationPermission;

public interface OperationPermissionDao extends IBaseDao<OperationPermission, String> {

	List<OperationPermission> findByFunctionPermissionId(String functionPermissionId);

}
