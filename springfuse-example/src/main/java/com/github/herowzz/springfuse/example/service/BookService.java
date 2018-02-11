package com.github.herowzz.springfuse.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.herowzz.springfuse.example.dao.BookDao;
import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.jpa.service.BaseService;

@Service
public class BookService extends BaseService<Book, String> {

	@Autowired
	private BookDao BookDao;

	@Override
	protected IBaseDao<Book, String> getEntityDao() {
		return BookDao;
	}

}
