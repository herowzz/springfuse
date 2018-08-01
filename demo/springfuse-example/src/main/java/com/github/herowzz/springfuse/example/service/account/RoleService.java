package com.github.herowzz.springfuse.example.service.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.data.jpa.service.BaseService;
import com.github.herowzz.springfuse.example.dao.account.RoleDao;
import com.github.herowzz.springfuse.example.domain.account.Role;

@Service
@CacheConfig(cacheNames = "role")
public class RoleService extends BaseService<Role, String> {

	@Autowired
	private RoleDao roleDao;

	@Override
	protected IBaseDao<Role, String> getEntityDao() {
		return roleDao;
	}

	@Transactional(readOnly = true)
	@Cacheable
	public Role getOne(@NonNull String id) {
		return getEntityDao().getOne(id);
	}

	@Transactional(readOnly = true)
	@Cacheable
	public Optional<Role> findById(String id) {
		return getEntityDao().findById(id);
	}

	@SuppressWarnings("unchecked")
	@CacheEvict(key = "#entity.id")
	public Role save(@NonNull Role entity) {
		return getEntityDao().save(entity);
	}

	@CacheEvict(key = "#entity.id")
	public void delete(@NonNull Role entity) {
		getEntityDao().delete(entity);
	}

}
