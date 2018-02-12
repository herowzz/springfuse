package com.github.herowzz.springfuse.example.dao;

import java.util.List;

import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;
import com.github.herowzz.springfuse.jpa.dao.IBaseDao;

public interface BookDao extends IBaseDao<Book, String> {

	List<Book> findByNameAndBookType(String name, BookTypeEnum bookType);

}
