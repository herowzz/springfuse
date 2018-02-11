package com.github.herowzz.springfuse.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.dto.book.BookDto;
import com.github.herowzz.springfuse.example.dto.book.param.AddBookParam;
import com.github.herowzz.springfuse.example.service.BookService;
import com.github.herowzz.springfuse.rest.dto.ResultDTO;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/list")
	public ResultDTO<List<BookDto>> list() {
		List<Book> bookList = bookService.findAll();
		List<BookDto> dtoList = bookList.stream().map(e -> BookDto.copy(e)).collect(Collectors.toList());
		return new ResultDTO<List<BookDto>>(dtoList);
	}

	@PostMapping(value = "/add")
	public ResultDTO<?> add(@RequestBody @Valid AddBookParam param) {
		Book book = param.copy();
		bookService.save(book);
		return ResultDTO.ok();
	}

}
