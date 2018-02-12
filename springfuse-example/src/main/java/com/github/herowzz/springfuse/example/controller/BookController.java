package com.github.herowzz.springfuse.example.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.dto.book.BookDto;
import com.github.herowzz.springfuse.example.dto.book.param.AddBookParam;
import com.github.herowzz.springfuse.example.dto.book.param.UpdateBookParam;
import com.github.herowzz.springfuse.example.service.BookService;
import com.github.herowzz.springfuse.rest.dto.IdParam;
import com.github.herowzz.springfuse.rest.dto.ApiResult;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping(value = "/list")
	public ApiResult<?> list() {
		List<Book> bookList = bookService.findAll();
		List<BookDto> dtoList = bookList.stream().map(e -> BookDto.copy(e)).collect(Collectors.toList());
		return ApiResult.build(dtoList);
	}

	@PostMapping(value = "/add")
	public ApiResult<?> add(@RequestBody @Valid AddBookParam param) {
		Book book = param.copy();
		bookService.save(book);
		return ApiResult.ok();
	}

	@PostMapping(value = "/{id}")
	public ApiResult<?> show(@PathVariable("id") String id) {
		Optional<Book> bookOptional = bookService.findById(id);
		if (!bookOptional.isPresent()) {
			return ApiResult.objectNotFound();
		}
		return ApiResult.build(BookDto.copy(bookOptional.get()));
	}

	@PostMapping(value = "/update")
	public ApiResult<?> update(@RequestBody @Valid UpdateBookParam param) {
		Optional<Book> bookOptional = bookService.findById(param.id);
		if (!bookOptional.isPresent()) {
			return ApiResult.objectNotFound();
		}
		bookService.save(param.copy(bookOptional.get()));
		return ApiResult.ok();
	}

	@PostMapping(value = "/delete")
	public ApiResult<?> delete(@RequestBody @Valid IdParam param) {
		bookService.delete(param.id);
		return ApiResult.ok();
	}

}
