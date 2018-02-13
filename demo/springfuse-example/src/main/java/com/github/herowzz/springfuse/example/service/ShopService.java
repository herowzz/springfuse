package com.github.herowzz.springfuse.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.data.jpa.service.BaseService;
import com.github.herowzz.springfuse.example.dao.ShopDao;
import com.github.herowzz.springfuse.example.domain.Shop;

@Service
@CacheConfig(cacheNames = "shop")
public class ShopService extends BaseService<Shop, String> {

	@Autowired
	private ShopDao shopDao;

	@Override
	protected IBaseDao<Shop, String> getEntityDao() {
		return shopDao;
	}

}
