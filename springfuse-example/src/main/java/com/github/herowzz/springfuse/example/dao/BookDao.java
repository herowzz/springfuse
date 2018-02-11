package com.github.herowzz.springfuse.example.dao;

import java.util.List;

import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.refrence.BookType;
import com.github.herowzz.springfuse.jpa.dao.IBaseDao;

public interface BookDao extends IBaseDao<Book, String> {

	public List<Book> findByNameAndBookType(String name, BookType bookType);

}
