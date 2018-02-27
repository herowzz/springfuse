package com.github.herowzz.springfuse.example.service;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.herowzz.springfuse.example.dao.BookDao;
import com.github.herowzz.springfuse.example.dao.ShopDao;
import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.Shop;

@RunWith(SpringRunner.class)
@Configuration
@SpringBootTest
@Transactional
public class ServiceTest {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private ShopDao shopDao;

	@Test
	@Rollback(false)
	public void exampleTest() {
		Book book = bookDao.findById("1").get();
		book.setName("wzz1");
		bookDao.save(book);

		Shop shop = book.getShop();
		shop.setName("22222");
		shopDao.save(shop);
	}
}
