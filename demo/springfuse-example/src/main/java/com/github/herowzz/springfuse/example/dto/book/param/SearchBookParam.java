package com.github.herowzz.springfuse.example.dto.book.param;

import com.github.herowzz.springfuse.api.dto.param.IBaseParam;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;

import io.swagger.annotations.ApiModelProperty;

public class SearchBookParam implements IBaseParam {

	@ApiModelProperty("图书名称")
	public String name;

	@ApiModelProperty("图书类型")
	public BookTypeEnum bookType;

	@ApiModelProperty("所属商店Id")
	public String shopId;

}
