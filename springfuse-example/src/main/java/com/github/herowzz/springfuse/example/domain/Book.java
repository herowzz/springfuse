package com.github.herowzz.springfuse.example.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;
import com.github.herowzz.springfuse.jpa.domain.BaseUidEntity;

@Entity
public class Book extends BaseUidEntity {

	private static final long serialVersionUID = -7001927790018290769L;

	private String name;

	private BookTypeEnum bookType;

	private int page;

	private LocalDateTime pubDate;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private Shop shop;

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

	public BookTypeEnum getBookType() {
		return bookType;
	}

	public void setBookType(BookTypeEnum bookType) {
		this.bookType = bookType;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [id=");
		builder.append(id);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append(", name=");
		builder.append(name);
		builder.append(", bookType=");
		builder.append(bookType);
		builder.append(", page=");
		builder.append(page);
		builder.append(", pubDate=");
		builder.append(pubDate);
		builder.append(", shop=");
		builder.append(shop.getId());
		builder.append("]");
		return builder.toString();
	}

}
