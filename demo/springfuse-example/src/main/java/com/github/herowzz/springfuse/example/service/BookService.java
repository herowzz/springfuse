package com.github.herowzz.springfuse.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.data.jpa.service.BaseService;
import com.github.herowzz.springfuse.example.dao.BookDao;
import com.github.herowzz.springfuse.example.domain.Book;

@Service
@CacheConfig(cacheNames = "book")
public class BookService extends BaseService<Book, String> {

	@Autowired
	private BookDao BookDao;

	@Override
	protected IBaseDao<Book, String> getEntityDao() {
		return BookDao;
	}

}
