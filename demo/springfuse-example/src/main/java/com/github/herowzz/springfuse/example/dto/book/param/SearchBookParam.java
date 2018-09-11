package com.github.herowzz.springfuse.example.dto.book.param;

import org.springframework.util.StringUtils;

import com.github.herowzz.springfuse.api.dto.param.IBaseParam;
import com.github.herowzz.springfuse.example.domain.QBook;
import com.github.herowzz.springfuse.example.domain.QShop;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import io.swagger.annotations.ApiModelProperty;

public class SearchBookParam implements IBaseParam {

	@ApiModelProperty("图书名称")
	public String name;

	@ApiModelProperty("图书类型")
	public BookTypeEnum bookType;

	@ApiModelProperty("所属商店Id")
	public String shopId;

	public Predicate build() {
		QBook book = QBook.book;
		BooleanBuilder qb = new BooleanBuilder();
		if (StringUtils.hasText(name)) {
			qb.and(book.name.like(name + "%"));
		}
		if (bookType != null) {
			qb.and(book.bookType.eq(bookType));
		}
		if (StringUtils.hasText(shopId)) {
			qb.and(QShop.shop.id.eq(shopId));
		}
		return qb;
	}

}
