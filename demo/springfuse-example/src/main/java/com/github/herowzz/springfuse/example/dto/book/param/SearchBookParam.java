package com.github.herowzz.springfuse.example.dto.book.param;

import com.github.herowzz.springfuse.example.domain.QBook;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;
import com.querydsl.core.types.Predicate;

public class SearchBookParam {

	public String name;

	public BookTypeEnum bookType;

	public Predicate build() {
		QBook book = QBook.book;
		Predicate pred = book.name.like(name + "%").and(book.bookType.eq(bookType));
		return pred;
	}

}
