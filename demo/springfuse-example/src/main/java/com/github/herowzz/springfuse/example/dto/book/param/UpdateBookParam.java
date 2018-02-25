package com.github.herowzz.springfuse.example.dto.book.param;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.Shop;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;

public class UpdateBookParam {

	@NotBlank
	public String id;

	@NotBlank
	public String shopId;

	@NotBlank
	public String name;

	@Max(100)
	@Min(10)
	@NotNull
	public Integer page;

	@NotNull
	public BookTypeEnum bookType;

	public BigDecimal sellPrice;

	public Book copy(Book book) {
		book.setName(this.name);
		book.setPage(this.page);
		book.setBookType(this.bookType);

		if (this.sellPrice != null)
			book.setSellPrice(this.sellPrice);

		Shop shop = new Shop();
		shop.setId(this.shopId);
		book.setShop(shop);

		return book;
	}

	@Override
	public String toString() {
		return "UpdateBookParam [id=" + id + ", shopId=" + shopId + ", name=" + name + ", page=" + page + ", bookType=" + bookType + "]";
	}

}
