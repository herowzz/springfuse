package com.github.herowzz.springfuse.example.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.herowzz.springfuse.api.dto.ApiResult;
import com.github.herowzz.springfuse.api.dto.param.IdParam;
import com.github.herowzz.springfuse.api.dto.param.PageParam;
import com.github.herowzz.springfuse.core.bean.page.PageCommon;
import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.dto.book.BookDto;
import com.github.herowzz.springfuse.example.dto.book.param.AddBookParam;
import com.github.herowzz.springfuse.example.dto.book.param.UpdateBookParam;
import com.github.herowzz.springfuse.example.service.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping(value = "/list")
	public ApiResult list(@RequestBody(required = false) @Valid PageParam pageParam) {
		Page<Book> bookPage = bookService.findPage(PageCommon.getPage(pageParam));
		Page<BookDto> bookDtoPage = bookPage.map(e -> BookDto.copy(e));
		return ApiResult.build(bookDtoPage);
	}

	@PostMapping(value = "/add")
	public ApiResult add(@RequestBody @Valid AddBookParam param) {
		Book book = param.copy();
		bookService.save(book);
		return ApiResult.ok();
	}

	@PostMapping(value = "/{id}")
	public ApiResult show(@PathVariable("id") Book book) {
		if (book == null) {
			return ApiResult.objectNotFound();
		}
		return ApiResult.build(BookDto.copy(book));
	}

	@PostMapping(value = "/update")
	public ApiResult update(@RequestBody @Valid UpdateBookParam param) {
		Optional<Book> bookOptional = bookService.findById(param.id);
		if (!bookOptional.isPresent()) {
			return ApiResult.objectNotFound();
		}
		bookService.save(param.copy(bookOptional.get()));
		return ApiResult.ok();
	}

	@PostMapping(value = "/delete")
	public ApiResult delete(@RequestBody @Valid IdParam param) {
		bookService.delete(param.id);
		return ApiResult.ok();
	}

}
