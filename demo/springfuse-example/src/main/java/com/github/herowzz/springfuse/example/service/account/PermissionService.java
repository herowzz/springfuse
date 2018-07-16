package com.github.herowzz.springfuse.example.service.account;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.herowzz.springfuse.example.dao.account.FunctionPermissionDao;
import com.github.herowzz.springfuse.example.dao.account.OperationPermissionDao;
import com.github.herowzz.springfuse.example.dao.account.RoleFunctionPermissionDao;
import com.github.herowzz.springfuse.example.domain.account.FunctionPermission;
import com.github.herowzz.springfuse.example.domain.account.OperationPermission;
import com.github.herowzz.springfuse.example.domain.account.Role;
import com.github.herowzz.springfuse.example.domain.account.RoleFunctionPermission;

@Service
@Transactional
public class PermissionService {

	@Autowired
	private FunctionPermissionDao functionPermissionDao;

	@Autowired
	private RoleFunctionPermissionDao roleFunctionPermissionDao;

	@Autowired
	private OperationPermissionDao operationPermissionDao;

	public FunctionPermission saveFunctionPermission(FunctionPermission functionPermission) {
		return functionPermissionDao.save(functionPermission);
	}

	public void saveFunctionPermissionList(List<FunctionPermission> functionPermissionList) {
		functionPermissionDao.saveAll(functionPermissionList);
	}

	public List<FunctionPermission> findAllFunctionPermission() {
		return functionPermissionDao.findAll();
	}

	public RoleFunctionPermission addRoleFunctionPermission(Role role, FunctionPermission functionPermission) {
		return roleFunctionPermissionDao.save(new RoleFunctionPermission(role, functionPermission));
	}

	public void addRoleFunctionPermission(Role role, List<FunctionPermission> functionPermissionList) {
		roleFunctionPermissionDao.saveAll(functionPermissionList.stream().map(r -> {
			return new RoleFunctionPermission(role, r);
		}).collect(Collectors.toList()));
	}

	public OperationPermission saveOperationPermission(OperationPermission operationPermission) {
		return operationPermissionDao.save(operationPermission);
	}

	public void saveOperationPermissionList(List<OperationPermission> operationPermissionList) {
		operationPermissionDao.saveAll(operationPermissionList);
	}

}
