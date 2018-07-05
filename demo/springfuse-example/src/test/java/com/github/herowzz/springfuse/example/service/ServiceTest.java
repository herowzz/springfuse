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

	@Autowired
	private BookService bookService;

	@Test
	@Rollback(false)
	public void exampleTest() {

		// Shop shop = new Shop();
		//// Shop shop = shopDao.findById("6b8b7c95-3296-47a8-acfd-daaac754db86").get();
		// shop.setAddress("1");
		// shop.setName("1");
		// shop = shopDao.save(shop);

		Shop shop2 = shopDao.findById("aeffb574-4dac-4792-a189-4192405e52c4").get();
		shop2.setAddress("61");
		shop2.setName("61");
		shopDao.save(shop2);

		Book book = new Book();
		book.setName("7771");
		book.setShop(shop2);
		bookDao.save(book);

		// Book book = bookDao.findById("1").get();
		// book.setName("wzz111");
		// bookDao.save(book);
		//
		// Shop shop = book.getShop();
		// shop.setName("22222");
		// shopDao.save(shop);
		//
		// Shop shopDel = shopDao.findById("4").get();
		// shopDao.delete(shopDel);

		bookService.findAll().stream().forEach(b -> System.out.println(b));

		// bookService.findPageEq(PageRequest.of(0, 10), "shop.name", "t").stream().forEach(book -> System.out.println(book));

		// bookDao.findTa(1).stream().forEach(book -> System.out.println(book));;
		// bookService.findYY();
		// bookDao.findTb("a").stream().forEach(book -> System.out.println(book));
	}
}
