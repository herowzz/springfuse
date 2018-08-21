package com.github.herowzz.springfuse.example.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.herowzz.springfuse.api.dto.ApiResult;
import com.github.herowzz.springfuse.api.dto.param.IdParam;
import com.github.herowzz.springfuse.api.log.annotation.Log;
import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.account.User;
import com.github.herowzz.springfuse.example.dto.book.BookDto;
import com.github.herowzz.springfuse.example.dto.book.param.AddBookParam;
import com.github.herowzz.springfuse.example.dto.book.param.SearchBookParam;
import com.github.herowzz.springfuse.example.dto.book.param.UpdateBookParam;
import com.github.herowzz.springfuse.example.service.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping(value = "/list")
	@Log(name = "图书管理", value = "查询图书")
	public ApiResult list(Pageable pageable, @RequestBody(required = false) @Valid SearchBookParam searchParam, @ModelAttribute("user") User user) {
		System.out.println(user);
		System.out.println(user.getId() + "---" + user.getUsername());
		Page<BookDto> bookDtoPage = bookService.findPage(pageable, searchParam != null ? searchParam.build() : null).map(e -> BookDto.copy(e));
		return ApiResult.build(bookDtoPage);
	}

	@PostMapping(value = "/list2")
	public ApiResult list2(Pageable pageable, @RequestBody(required = false) @Valid SearchBookParam searchParam, @ModelAttribute("user") User user) {
		List<Book> bookList = bookService.findDD(searchParam.name, searchParam.bookType);
		Stream<Object> bookDto = bookList.stream().map(e -> BookDto.copy(e));
		return ApiResult.build(bookDto);
	}

	@PostMapping(value = "/add")
	@Log(name = "图书管理", value = "新增图书")
	public ApiResult add(@RequestBody @Valid AddBookParam param) {
		Book book = param.copy();
		bookService.save(book);
		return ApiResult.ok();
	}

	@PostMapping(value = "/{id}")
	public ApiResult show(@PathVariable("id") Book book, @ModelAttribute(name = "user", binding = false) User user) {
		System.out.println(user);
		System.out.println(user.getId() + "---" + user.getUsername());
		if (book == null) {
			return ApiResult.objectNotFound();
		}
		return ApiResult.build(BookDto.copy(book));
	}

	@PostMapping(value = "/update")
	@Log(name = "图书管理", value = "修改图书")
	public ApiResult update(@RequestBody @Valid UpdateBookParam param, @ModelAttribute("user") User user) {
		System.out.println(user);
		System.out.println(user.getId() + "---" + user.getUsername());
		Optional<Book> bookOptional = bookService.findById(param.id);
		if (!bookOptional.isPresent()) {
			return ApiResult.objectNotFound();
		}
		bookService.save(param.copy(bookOptional.get()));
		return ApiResult.ok();
	}

	@PostMapping(value = "/delete")
	@Log(name = "图书管理", value = "删除图书")
	public ApiResult delete(@RequestBody @Valid IdParam param) {
		bookService.delete(param.id);
		return ApiResult.ok();
	}

}
