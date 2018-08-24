package com.github.herowzz.springfuse.doc.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
		// 页码
		@ApiImplicitParam(name = "page", dataType = "int", paramType = "query", value = "第几页，从0开始，默认为第0页"),
		// 每页数量
		@ApiImplicitParam(name = "size", dataType = "int", paramType = "query", value = "每一页的大小，默认为20"),
		// 排序
		@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "排序相关的信息(例如sort=firstname&sort=lastname,desc表示在按firstname正序排列基础上按lastname倒序排列)") })
public @interface ApiPageable {
}