package com.github.herowzz.springfuse.example.dto.book.param;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.herowzz.springfuse.api.dto.param.IBaseParam;
import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.Shop;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "新增图书对象")
public class AddBookParam implements IBaseParam {

	@NotBlank
	@ApiModelProperty("图书名称")
	public String name;

	@NotBlank
	@ApiModelProperty("所属商店Id")
	public String shopId;

	@Max(100)
	@Min(10)
	@NotNull
	@ApiModelProperty("页码")
	public Integer page;

	@NotNull
	@ApiModelProperty("图书类型")
	public BookTypeEnum bookType;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("发布时间")
	public LocalDateTime pubDate;

	@ApiModelProperty("销售价格")
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
