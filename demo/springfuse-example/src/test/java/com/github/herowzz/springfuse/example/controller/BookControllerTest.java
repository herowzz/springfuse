package com.github.herowzz.springfuse.example.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.account.User;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;
import com.github.herowzz.springfuse.example.service.BookService;
import com.github.herowzz.springfuse.example.service.account.UserService;
import com.github.herowzz.springfuse.security.manager.ITokenManager;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@EnableSpringDataWebSupport
public class BookControllerTest {

	public static final String TOKEN = "1";

	@Autowired
	private MockMvc mvc;

	@MockBean
	private BookService bookService;

	@MockBean
	private UserService userService;

	@MockBean
	private ITokenManager tokenManager;

	@Before
	public void before() {
		given(tokenManager.getUidByToken(TOKEN)).willReturn("1");
		User user = new User();
		user.setId("1");
		user.setUsername("spring");
		given(userService.findById(TOKEN)).willReturn(Optional.of(user));
	}

	@Test
	public void testList() throws Exception {
		Book book1 = new Book();
		book1.setBookType(BookTypeEnum.ECONOMIC);
		book1.setName("aaa");
		book1.setId("001");
		book1.setPage(100);
		book1.setPubDate(LocalDateTime.now());
		List<Book> bookList = Arrays.asList(book1);
		Page<Book> page = new PageImpl<>(bookList);

		given(bookService.findBy(PageRequest.of(0, 20), "", null, "")).willReturn(page);

		String requestBody = "{\"name\":\"\", \"bookType\": null, \"shopId\":\"\"}";
		mvc.perform(post("/book/list").header("token", TOKEN).contentType(MediaType.APPLICATION_JSON_UTF8).content(requestBody)
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andExpect(jsonPath("$.code").value(1)).andExpect(jsonPath("$.data.content[0].name").value("aaa"));
	}

}
