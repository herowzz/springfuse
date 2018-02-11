package com.github.herowzz.springfuse.example.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.refrence.BookType;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookDaoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BookDao bookDao;

	@Test
	public void testFindByNameAndBookType() {
		Book book = new Book();
		book.setBookType(BookType.Economic);
		book.setName("a" + 1);
		book.setPage(1 * 10);
		book.setPubDate(LocalDateTime.now());
		entityManager.persist(book);

		List<Book> bookList = this.bookDao.findByNameAndBookType("a1", BookType.Economic);
		assertThat(bookList.size()).isEqualTo(1);
		assertThat(bookList.get(0).getName()).isEqualTo("a1");
		assertThat(bookList.get(0).getPage()).isEqualTo(10);
	}

}
