package com.github.herowzz.springfuse.example.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.data.jpa.service.BaseService;
import com.github.herowzz.springfuse.example.dao.BookDao;
import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.QBook;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;

@Service
public class BookService extends BaseService<Book, String> {

	@Autowired
	private BookDao bookDao;

	@Override
	protected IBaseDao<Book, String> getEntityDao() {
		return bookDao;
	}

	public List<Book> findYY() {
		return bookDao.findAll((root, query, cb) -> {
			Path<LocalDateTime> expression = root.get("pubDate");
			Predicate dateWhere = cb.between(expression, LocalDateTime.now(), LocalDateTime.now());
			query.where(dateWhere);
			query.orderBy(cb.desc(expression));
			return query.getRestriction();
		});
	}

	public List<Book> findDD(String name, BookTypeEnum bookType) {
		QBook book = QBook.book;
		com.querydsl.core.types.Predicate p = book.shop.name.like(name).and(book.bookType.eq(bookType));
		Iterable<Book> it = bookDao.findAll(p);
		return (List<Book>) it;
	}

}
