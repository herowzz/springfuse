package com.github.herowzz.springfuse.example.dao;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;

@RunWith(SpringRunner.class)
// @DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class BookDaoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BookDao bookDao;

	@Before
	@Transactional
	public void initData() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			Book book = new Book();
			book.setBookType(BookTypeEnum.ECONOMIC);
			book.setName("a" + i);
			book.setPage(i * 10);
			book.setPubDate(LocalDateTime.now());
			entityManager.persist(book);
		});

	}

	// @Test
	public void testFindByNameAndBookType() {
		List<Book> bookList = bookDao.findByNameAndBookType("a1", BookTypeEnum.ECONOMIC);
		assertThat(bookList.size()).isEqualTo(1);
		assertThat(bookList.get(0).getName()).isEqualTo("a1");
		assertThat(bookList.get(0).getPage()).isEqualTo(10);
	}

	// @Test
	@Transactional
	@Rollback(false)
	public void testFindByIdForUpdate() {
		Book book1 = bookDao.findOne((root, query, cb) -> cb.equal(root.get("name"), "a1")).get();
		Book book2 = bookDao.findByIdForUpdate(book1.getId());
		assertThat(book2.getPage()).isEqualTo(10);
	}

	// @Test
	@Rollback(false)
	public void testDynamicUpdate() {
		Book book1 = bookDao.findOne((root, query, cb) -> cb.equal(root.get("name"), "a1")).get();
		book1.setPage(99);
		// bookDao.save(book1);
	}

	@Test
	public void testFind() {
		List<Book> bookList = bookDao.findByNameAndBookType("a1", BookTypeEnum.ECONOMIC);
		System.out.println(bookList.size());

		Optional<Book> book = bookDao.findById("635a2993-7e78-40e9-81e1-61073bfd1901");
		System.out.println(book.isPresent());
	}

}
