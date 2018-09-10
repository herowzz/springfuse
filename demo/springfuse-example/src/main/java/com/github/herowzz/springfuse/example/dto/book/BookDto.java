package com.github.herowzz.springfuse.example.dto.book;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "图书对象")
public class BookDto {

	@ApiModelProperty(value = "图书id")
	public String id;

	@ApiModelProperty("图书名称")
	public String name;

	@ApiModelProperty("图书类型")
	public BookTypeEnum bookType;

	@ApiModelProperty("页码")
	public int page;

	@ApiModelProperty("销售价格")
	public BigDecimal sellPrice;

	@ApiModelProperty("发布时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime pubDate;

	@ApiModelProperty(value = "所属商店Id")
	public String shopId;

	@ApiModelProperty(value = "所属商店名称")
	public String shopName;

	public static BookDto copy(Book book) {
		BookDto dto = new BookDto();
		if (book != null) {
			dto.id = book.getId();
			dto.name = book.getName();
			dto.bookType = book.getBookType();
			dto.page = book.getPage();
			dto.pubDate = book.getPubDate();
			dto.sellPrice = book.getSellPrice();
			dto.shopId = book.getShop().getId();
			dto.shopName = book.getShop().getName();
		}
		return dto;
	}

}
