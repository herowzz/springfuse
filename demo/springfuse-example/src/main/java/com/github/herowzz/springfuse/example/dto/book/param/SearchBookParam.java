package com.github.herowzz.springfuse.example.dto.book.param;

import org.springframework.util.StringUtils;

import com.github.herowzz.springfuse.example.domain.QBook;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class SearchBookParam {

	public String name;

	public BookTypeEnum bookType;

	public Predicate build() {
		QBook book = QBook.book;
		BooleanBuilder qb = new BooleanBuilder();
		if (StringUtils.hasText(name)) {
			qb.and(book.name.like(name + "%"));
		}
		if (bookType != null) {
			qb.and(book.bookType.eq(bookType));
		}
		return qb;
	}

}
