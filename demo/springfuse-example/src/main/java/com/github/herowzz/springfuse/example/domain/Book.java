package com.github.herowzz.springfuse.example.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.github.herowzz.springfuse.data.jpa.domain.BaseUidEntity;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;

@Entity
public class Book extends BaseUidEntity {

	private static final long serialVersionUID = -7001927790018290769L;

	private String name;

	private BookTypeEnum bookType;

	private int page;

	private BigDecimal sellPrice;

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

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [name=");
		builder.append(name);
		builder.append(", bookType=");
		builder.append(bookType);
		builder.append(", page=");
		builder.append(page);
		builder.append(", sellPrice=");
		builder.append(sellPrice);
		builder.append(", pubDate=");
		builder.append(pubDate);
		builder.append(", shop=");
		builder.append(shop);
		builder.append("]");
		return builder.toString();
	}

}
