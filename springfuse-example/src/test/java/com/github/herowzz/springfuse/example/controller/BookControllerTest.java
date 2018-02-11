package com.github.herowzz.springfuse.example.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.refrence.BookType;
import com.github.herowzz.springfuse.example.dto.book.BookDto;
import com.github.herowzz.springfuse.example.service.BookService;
import com.github.herowzz.springfuse.rest.dto.ResultDTO;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

	@Mock
	private BookService bookService;

	@InjectMocks
	private BookController bookController = new BookController();

	@Test
	public void testList() throws Exception {
		Book book1 = new Book();
		book1.setBookType(BookType.Economic);
		book1.setName("aaa");
		book1.setId("001");
		book1.setPage(100);
		book1.setPubDate(LocalDateTime.now());
		List<Book> bookList = Arrays.asList(book1);

		given(bookService.findAll()).willReturn(bookList);

		ResultDTO<List<BookDto>> resultDto = bookController.list();
		assertThat(resultDto.code).isEqualTo(1);

		List<BookDto> bookDtoList = resultDto.data;
		assertThat(bookDtoList.size()).isEqualTo(1);
		assertThat(bookDtoList.get(0).name).isEqualTo("aaa");

	}

}
