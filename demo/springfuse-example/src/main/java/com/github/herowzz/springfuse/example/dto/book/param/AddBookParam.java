package com.github.herowzz.springfuse.example.dto.book.param;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.Shop;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;

public class AddBookParam {

	@NotBlank
	public String name;

	@NotBlank
	public String shopId;

	@Max(100)
	@Min(10)
	@NotNull
	public Integer page;

	@NotNull
	public BookTypeEnum bookType;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime pubDate;

	public BigDecimal sellPrice;

	public Book copy() {
		Book book = new Book();
		book.setName(this.name);
		book.setPage(this.page);
		book.setBookType(this.bookType);
		book.setPubDate(this.pubDate);
		book.setSellPrice(this.sellPrice);

		Shop shop = new Shop();
		shop.setId(this.shopId);
		book.setShop(shop);

		return book;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddBookParam [name=");
		builder.append(name);
		builder.append(", shopId=");
		builder.append(shopId);
		builder.append(", page=");
		builder.append(page);
		builder.append(", bookType=");
		builder.append(bookType);
		builder.append(", pubDate=");
		builder.append(pubDate);
		builder.append(", sellPrice=");
		builder.append(sellPrice);
		builder.append("]");
		return builder.toString();
	}

}
