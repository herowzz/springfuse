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
import com.github.herowzz.springfuse.example.dao.account.UserDao;
import com.github.herowzz.springfuse.example.domain.account.User;

@Service
@CacheConfig(cacheNames = "user")
public class UserService extends BaseService<User, String> {

	@Autowired
	private UserDao userDao;

	@Override
	protected IBaseDao<User, String> getEntityDao() {
		return userDao;
	}

	@Transactional(readOnly = true)
	@Cacheable
	public User getOne(@NonNull String id) {
		return getEntityDao().getOne(id);
	}

	@Transactional(readOnly = true)
	@Cacheable
	public Optional<User> findById(String id) {
		return getEntityDao().findById(id);
	}

	@SuppressWarnings("unchecked")
	@CacheEvict(key = "#entity.id")
	public User save(@NonNull User entity) {
		return getEntityDao().save(entity);
	}

	@CacheEvict(key = "#entity.id")
	public void delete(@NonNull User entity) {
		getEntityDao().delete(entity);
	}

}
