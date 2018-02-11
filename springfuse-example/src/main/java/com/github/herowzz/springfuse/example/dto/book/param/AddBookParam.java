package com.github.herowzz.springfuse.example.dto.book.param;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.refrence.BookType;

public class AddBookParam {

	@NotBlank
	public String name;

	@Max(100)
	@Min(10)
	@NotNull
	public Integer page;

	@NotNull
	public BookType bookType;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime pubDate;

	public Book copy() {
		Book book = new Book();
		book.setName(this.name);
		book.setPage(this.page);
		book.setPubDate(this.pubDate);
		return book;
	}

	@Override
	public String toString() {
		return "AddBookParam [name=" + name + ", page=" + page + ", bookType=" + bookType + ", pubDate=" + pubDate + "]";
	}

}
