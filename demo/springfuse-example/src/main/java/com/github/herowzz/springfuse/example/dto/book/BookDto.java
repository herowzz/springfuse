package com.github.herowzz.springfuse.example.dto.book;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;

public class BookDto {

	public String id;

	public String name;

	public BookTypeEnum bookType;

	public int page;

	public BigDecimal sellPrice;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime pubDate;

	public static BookDto copy(Book book) {
		BookDto dto = new BookDto();
		dto.id = book.getId();
		dto.name = book.getName();
		dto.bookType = book.getBookType();
		dto.page = book.getPage();
		dto.pubDate = book.getPubDate();
		dto.sellPrice = book.getSellPrice();
		return dto;
	}

}