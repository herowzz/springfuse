package com.github.herowzz.springfuse.example.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.github.herowzz.springfuse.example.domain.refrence.BookType;

@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = 1989855612074165969L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	private String id;

	private String name;
	
	private BookType bookType;

	private int page;

	private LocalDateTime pubDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public LocalDateTime getPubDate() {
		return pubDate;
	}

	public void setPubDate(LocalDateTime pubDate) {
		this.pubDate = pubDate;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

}
