package com.github.herowzz.springfuse.example.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.data.jpa.service.BaseService;
import com.github.herowzz.springfuse.example.dao.BookDao;
import com.github.herowzz.springfuse.example.domain.Book;
import com.github.herowzz.springfuse.example.domain.refrence.BookTypeEnum;

@Service
public class BookService extends BaseService<Book, String> {

	@Autowired
	private BookDao bookDao;

	@Override
	protected IBaseDao<Book, String> getEntityDao() {
		return bookDao;
	}

	public List<Book> findYY() {
		return bookDao.findAll((root, query, cb) -> {
			Path<LocalDateTime> expression = root.get("pubDate");
			Predicate dateWhere = cb.between(expression, LocalDateTime.now(), LocalDateTime.now());
			query.where(dateWhere);
			query.orderBy(cb.desc(expression));
			return query.getRestriction();
		});
	}

	public Page<Book> findBy(Pageable pageable, String name, BookTypeEnum bookType, String shopId) {
		return bookDao.findAll((root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.hasText(name)) {
				predicates.add(cb.equal(root.get("name"), name));
			}
			if (bookType != null) {
				predicates.add(cb.equal(root.get("bookType"), bookType));
			}
			if (StringUtils.hasText(shopId)) {
				predicates.add(cb.equal(root.get("shop").get("id"), shopId));
			}
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		}, pageable);
	}

}
