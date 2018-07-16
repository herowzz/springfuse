package com.github.herowzz.springfuse.example.startup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.github.herowzz.springfuse.example.domain.account.FunctionPermission;
import com.github.herowzz.springfuse.example.domain.account.OperationPermission;
import com.github.herowzz.springfuse.example.domain.account.Role;
import com.github.herowzz.springfuse.example.domain.account.User;
import com.github.herowzz.springfuse.example.service.account.PermissionService;
import com.github.herowzz.springfuse.example.service.account.RoleService;
import com.github.herowzz.springfuse.example.service.account.UserService;

/**
 * 管理员数据初始化<br>
 * 初始化admin超级管理员,以及该管理员的角色和所有权限
 * @author wangzz
 */
@Component
public class DbAdminDataInit implements ApplicationRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		User admin = initAdminUser();
		Role adminRole = initRole();
		List<Role> adminRoleList = roleService.findRoleListByUser(admin.getId());
		Optional<Role> adminRoleOptional = adminRoleList.stream().filter(r -> r.getName().equals("超级管理员")).findAny();
		if (!adminRoleOptional.isPresent()) {
			roleService.addRole(admin, adminRole);
		}
		initPermission(adminRole);
	}

	/**
	 * 初始化超级管理员
	 * @return 如果已存在则返回该用户
	 */
	private User initAdminUser() {
		Optional<User> optionalUser = userService.findOneEq("username", "admin");
		if (!optionalUser.isPresent()) {
			User user = new User();
			user.setUsername("admin");
			user.setPassword("admin");
			user.setRealname("超级管理员");
			user.setNickname("超级管理员");
			user.setCreateUserName("系统初始化");
			return userService.save(user);
		}
		return optionalUser.get();
	}

	/**
	 * 初始化管理员角色
	 * @return 如果已存在则返回该角色
	 */
	private Role initRole() {
		Optional<Role> optionalRole = roleService.findOneEq("name", "超级管理员");
		if (!optionalRole.isPresent()) {
			Role role = new Role("超级管理员", 0);
			role.setCreateUserName("系统初始化");
			return roleService.save(role);
		}
		return optionalRole.get();
	}

	/**
	 * 初始化权限
	 */
	private void initPermission(Role adminRole) {
		if (permissionService.findAllFunctionPermission().isEmpty()) {
			FunctionPermission functionBook = new FunctionPermission("bookManage", "书籍管理", 110000);
			functionBook = permissionService.saveFunctionPermission(functionBook);

			List<FunctionPermission> functionBookPermissionList = new ArrayList<>();
			FunctionPermission functionBookList = new FunctionPermission(functionBook, "bookList", "书籍列表", 110100);
			functionBookPermissionList.add(functionBookList);
			FunctionPermission functionBookSetup = new FunctionPermission(functionBook, "bookSetup", "书籍配置", 110200);
			functionBookPermissionList.add(functionBookSetup);
			permissionService.saveFunctionPermissionList(functionBookPermissionList);
			permissionService.addRoleFunctionPermission(adminRole, functionBookPermissionList);

			List<OperationPermission> operationBookPermissionList = new ArrayList<>();
			operationBookPermissionList.add(new OperationPermission(functionBookList, "bookAdd", "添加书籍", 110101));
			operationBookPermissionList.add(new OperationPermission(functionBookList, "bookEdit", "修改书籍", 110102));
			operationBookPermissionList.add(new OperationPermission(functionBookSetup, "bookSetupSave", "配置保存", 110201));
			permissionService.saveOperationPermissionList(operationBookPermissionList);
		}
	}

}
