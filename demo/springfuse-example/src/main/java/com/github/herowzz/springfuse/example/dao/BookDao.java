package com.github.herowzz.springfuse.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;

public interface BookDao extends IBaseDao<Book, String> {

	List<Book> findByNameAndBookType(String name, BookTypeEnum bookType);

	@Query("from Book b where b.name like ?1")
	List<Book> findTb(String name);

	@Query(value = "select * from book b where b.id= :id and b.page = 11", nativeQuery = true)
	List<Book> findTa(@Param("id") String id);
}
