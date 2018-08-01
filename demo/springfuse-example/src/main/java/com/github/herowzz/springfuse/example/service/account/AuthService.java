package com.github.herowzz.springfuse.example.service.account;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.herowzz.springfuse.example.dao.account.PermissionDao;
import com.github.herowzz.springfuse.example.dao.account.RolePermissionDao;
import com.github.herowzz.springfuse.example.dao.account.UserDao;
import com.github.herowzz.springfuse.example.dao.account.UserRoleDao;
import com.github.herowzz.springfuse.example.domain.account.Permission;
import com.github.herowzz.springfuse.example.domain.account.Role;
import com.github.herowzz.springfuse.example.domain.account.RolePermission;
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
	private PermissionDao permissionDao;

	@Autowired
	private RolePermissionDao rolePermissionDao;

	/**
	 * 根据用户登录名密码查询用户对象
	 * @param username 登录名
	 * @param password 登录密码
	 * @return 用户对象.不存在返回Null
	 */
	@Cacheable("user")
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
	@CachePut(value = "user", key = "#user.id")
	public User login(User user, String ip) {
		user.setLastLoginIp(ip);
		user.setLastLoginTime(LocalDateTime.now());
		user = userDao.save(user);
		return user;
	}

	@Cacheable("user")
	public User findUserPermission(User user) {
		Map<String, Permission> userPermMap = this.findPermissionMap(user.getId());
		user.setPermissionMap(userPermMap);
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
	 * 获取用户下的权限
	 * @param userId 用户id
	 * @return 权限Map(key:权限码,value:权限对象)
	 */
	public Map<String, Permission> findPermissionMap(String userId) {
		List<Role> roleList = this.findRoleListByUser(userId);
		return this.findPermissions(roleList).stream().collect(Collectors.toMap(p -> p.getCode(), p -> p));
	}

	/**
	 * 获取角色下的权限
	 * @param roleList 角色列表
	 * @return 权限Set(去重)
	 */
	public Set<Permission> findPermissions(List<Role> roleList) {
		Set<Permission> permissions = new HashSet<>();
		for (Role role : roleList) {
			rolePermissionDao.findByRoleId(role.getId()).forEach(rp -> permissions.add(rp.getPermission()));
		}
		return permissions;
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

	/**
	 * 为角色关联权限
	 * @param role 角色
	 * @param permission 权限
	 * @return 角色权限对象
	 */
	public void relateRolePermission(Role role, List<Permission> permission) {
		List<RolePermission> rolePermissionList = new ArrayList<>();
		permission.forEach(p -> rolePermissionList.add(new RolePermission(role, p)));
		rolePermissionDao.saveAll(rolePermissionList);
	}

	/**
	 * 获取所有权限
	 * @return 权限列表
	 */
	public List<Permission> findAllPermission() {
		return permissionDao.findAll();
	}

	/**
	 * 保存权限
	 * @param permission 权限对象
	 * @return 保存后的权限对象
	 */
	public Permission savePermission(Permission permission) {
		return permissionDao.save(permission);
	}

	/**
	 * 保存多条权限
	 * @param permissionList 权限列表
	 */
	public void savePermissionList(List<Permission> permissionList) {
		permissionDao.saveAll(permissionList);
	}

}
