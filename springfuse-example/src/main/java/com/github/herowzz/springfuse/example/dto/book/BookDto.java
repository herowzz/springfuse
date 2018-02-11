package com.github.herowzz.springfuse.example.dto.book;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.refrence.BookType;

public class BookDto {

	public String id;

	public String name;

	public BookType bookType;

	public int page;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime pubDate;

	public static BookDto copy(Book book) {
		BookDto dto = new BookDto();
		dto.id = book.getId();
		dto.name = book.getName();
		dto.bookType = book.getBookType();
		dto.page = book.getPage();
		dto.pubDate = book.getPubDate();
		return dto;
	}

}
