package com.github.herowzz.springfuse.example.service.account;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.herowzz.springfuse.example.dao.account.FunctionPermissionDao;
import com.github.herowzz.springfuse.example.dao.account.OperationPermissionDao;
import com.github.herowzz.springfuse.example.dao.account.RoleFunctionPermissionDao;
import com.github.herowzz.springfuse.example.dao.account.UserDao;
import com.github.herowzz.springfuse.example.dao.account.UserRoleDao;
import com.github.herowzz.springfuse.example.domain.account.FunctionPermission;
import com.github.herowzz.springfuse.example.domain.account.OperationPermission;
import com.github.herowzz.springfuse.example.domain.account.Role;
import com.github.herowzz.springfuse.example.domain.account.RoleFunctionPermission;
import com.github.herowzz.springfuse.example.domain.account.User;
import com.github.herowzz.springfuse.example.domain.account.UserRole;

@Service
@Transactional
public class AuthService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private FunctionPermissionDao functionPermissionDao;

	@Autowired
	private RoleFunctionPermissionDao roleFunctionPermissionDao;

	@Autowired
	private OperationPermissionDao operationPermissionDao;

	/**
	 * 根据用户登录名密码查询用户对象
	 * @param username 登录名
	 * @param password 登录密码
	 * @return 用户对象.不存在返回Null
	 */
	public User findUserByUsernameAndPassword(String username, String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}

	/**
	 * 登录逻辑<br>
	 * 记录登录IP和登录时间
	 * @param user 登录用户
	 * @param ip 访问IP地址
	 * @return 处理后的用户对象
	 */
	public User login(User user, String ip) {
		user.setLastLoginIp(ip);
		user.setLastLoginTime(LocalDateTime.now());
		user = userDao.save(user);
		return user;
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

	/**
	 * 为用户关联角色
	 * @param user 用户对象
	 * @param role 需要关联的角色
	 * @return 用户角色关联对象
	 */
	public UserRole relateUserRole(User user, Role role) {
		return userRoleDao.save(new UserRole(user, role));
	}

	public FunctionPermission saveFunctionPermission(FunctionPermission functionPermission) {
		return functionPermissionDao.save(functionPermission);
	}

	public void saveFunctionPermissionList(List<FunctionPermission> functionPermissionList) {
		functionPermissionDao.saveAll(functionPermissionList);
	}

	public List<FunctionPermission> findAllFunctionPermission() {
		return functionPermissionDao.findAll();
	}

	public List<FunctionPermission> findFunctionPermissionByRole(List<Role> roleList) {
		List<FunctionPermission> functionPermissionList = new ArrayList<>();
		roleList.forEach(r -> {
			roleFunctionPermissionDao.findByRoleId(r.getId()).stream().forEach(rf -> {
				functionPermissionList.add(rf.getFunctionPermission());
			});
		});
		functionPermissionList.sort((f1, f2) -> f1.getNum().compareTo(f2.getNum()));
		return functionPermissionList;
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

	public List<OperationPermission> findByFunctionPermission(String functionPermissionId) {
		List<OperationPermission> operPermList = operationPermissionDao.findByFunctionPermissionId(functionPermissionId);
		operPermList.sort((f1, f2) -> f1.getNum().compareTo(f2.getNum()));
		return operPermList;
	}

}
