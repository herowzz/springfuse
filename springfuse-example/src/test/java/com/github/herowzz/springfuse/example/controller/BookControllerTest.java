package com.github.herowzz.springfuse.example.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;
import com.github.herowzz.springfuse.example.service.BookService;
import com.github.herowzz.springfuse.rest.test.BaseControllerTest;

@WebMvcTest(BookController.class)
public class BookControllerTest extends BaseControllerTest {

	@MockBean
	private BookService bookService;

	@Test
	public void testList() throws Exception {
		Book book1 = new Book();
		book1.setBookType(BookTypeEnum.ECONOMIC);
		book1.setName("aaa");
		book1.setId("001");
		book1.setPage(100);
		book1.setPubDate(LocalDateTime.now());
		List<Book> bookList = Arrays.asList(book1);

		given(bookService.findAll()).willReturn(bookList);

		mvc.perform(post("/book/list").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andExpect(jsonPath("$.code").value(1)).andExpect(jsonPath("$.data[0].name").value("aaa"));
	}

}
