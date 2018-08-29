package com.github.herowzz.springfuse.data.jpa.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.querydsl.core.types.OrderSpecifier;

@Transactional
public abstract class BaseService<T, ID extends Serializable> {

	/**
	 * 获取当前dao<br>
	 * 子类实现此方法注入dao
	 * @return 继承IBaseDao的dao
	 */
	protected abstract IBaseDao<T, ID> getEntityDao();

	/**
	 * 根据Id查询实体<br>
	 * @param id 实体主键
	 * @return 实体
	 * @throws javax.persistence.EntityNotFoundException if no entity exists for given {@code id}.
	 */
	@Transactional(readOnly = true)
	public T getOne(@NonNull ID id) {
		return getEntityDao().getOne(id);
	}

	/**
	 * 根据Id查询实体<br>
	 * @param id 实体主键
	 * @return 实体Optional
	 */
	@Transactional(readOnly = true)
	public Optional<T> findById(ID id) {
		return getEntityDao().findById(id);
	}

	/**
	 * Flushes all pending changes to the database.
	 */
	public void flush() {
		getEntityDao().flush();
	}

	/**
	 * 更新实体
	 * @param <S> 继承本实体的对象类型
	 * @param entity 实体对象
	 * @return 更新后的实体
	 */
	public <S extends T> S saveAndFlush(@NonNull S entity) {
		return getEntityDao().saveAndFlush(entity);
	}

	/**
	 * 更新实体
	 * @param <S> 继承本实体的对象类型
	 * @param entity 实体对象
	 * @return 更新后的实体
	 */
	public <S extends T> S save(@NonNull S entity) {
		return getEntityDao().save(entity);
	}

	/**
	 * 批量更新实体
	 * @param <S> 实体类型
	 * @param entities 实体列表
	 * @return 更新后的实体
	 */
	public <S extends T> List<S> saveAll(@NonNull Iterable<S> entities) {
		return getEntityDao().saveAll(entities);
	}

	/**
	 * 批量更新实体
	 * @param <S> 实体类型
	 * @param entities 实体列表
	 * @return 更新后的实体
	 */
	public <S extends T> List<S> saveAll(@NonNull S[] entities) {
		return saveAll(Arrays.asList(entities));
	}

	/**
	 * 删除实体
	 * @param entity 实体对象
	 */
	public void delete(@NonNull T entity) {
		getEntityDao().delete(entity);
	}

	/**
	 * 根据Id删除实体
	 * @param id 主键
	 */
	public void delete(@NonNull ID id) {
		Optional<T> optionalT = this.findById(id);
		if (optionalT.isPresent()) {
			this.delete(optionalT.get());
		}
	}

	/**
	 * 根据Id数组批量删除实体
	 * @param ids 实体主键列表
	 */
	public void delete(@NonNull ID[] ids) {
		for (ID id : ids) {
			delete(id);
		}
	}

	/**
	 * 根据实体列表批量删除
	 * @param entities 实体列表
	 */
	public void deleteAll(@NonNull Iterable<? extends T> entities) {
		getEntityDao().deleteAll(entities);
	}

	/**
	 * 根据实体列表批量删除
	 * @param entities 实体列表
	 */
	public void deleteAll(@NonNull T[] entities) {
		for (T entity : entities) {
			getEntityDao().delete(entity);
		}
	}

	/**
	 * 删除所有实体
	 */
	public void deleteAll() {
		getEntityDao().deleteAll();
	}

	/**
	 * 删除所有实体(批量)
	 * @param entities 实体列表
	 */
	public void deleteInBatch(@NonNull Iterable<T> entities) {
		getEntityDao().deleteInBatch(entities);
	}

	/**
	 * 删除所有实体(批量)
	 */
	public void deleteAllInBatch() {
		getEntityDao().deleteAllInBatch();
	}

	/**
	 * 返回所有实体数量
	 * @return 实体个数
	 */
	@Transactional(readOnly = true)
	public long count() {
		return getEntityDao().count();
	}

	/**
	 * 根据查询条件返回符合条件结果数量
	 * @param spec 查询条件
	 * @return 实体个数
	 */
	@Transactional(readOnly = true)
	public long count(Specification<T> spec) {
		return getEntityDao().count(spec);
	}

	/**
	 * 根据查询条件返回符合条件结果数量
	 * @param predicate querydsl查询条件
	 * @return 实体个数
	 */
	@Transactional(readOnly = true)
	public long count(com.querydsl.core.types.Predicate predicate) {
		return getEntityDao().count(predicate);
	}

	/**
	 * 根据查询条件返回符合条件结果数量
	 * @param <S> 实体类型
	 * @param example 查询条件
	 * @return 实体个数
	 */
	@Transactional(readOnly = true)
	public <S extends T> long count(Example<S> example) {
		return getEntityDao().count(example);
	}

	/**
	 * 判断此id的实体是否存在
	 * @param id 主键ID
	 * @return 存在返回true
	 */
	@Transactional(readOnly = true)
	public boolean exists(@NonNull ID id) {
		return getEntityDao().existsById(id);
	}

	/**
	 * 判断匹配的条件是否存在
	 * @param <S> 实体类型
	 * @param example 匹配条件
	 * @return 存在返回true
	 */
	@Transactional(readOnly = true)
	public <S extends T> boolean exists(Example<S> example) {
		return getEntityDao().exists(example);
	}

	/**
	 * 判断匹配的条件是否存在
	 * @param predicate querydsl匹配条件
	 * @return 存在返回true
	 */
	@Transactional(readOnly = true)
	public boolean exists(com.querydsl.core.types.Predicate predicate) {
		return getEntityDao().exists(predicate);
	}

	/**
	 * 判断该参数是否存在
	 * @param paramName 参数名称
	 * @param newValue 输入值
	 * @param oldValue 原值
	 * @return 不存在返回true，存在返回false
	 */
	@Transactional(readOnly = true)
	public boolean checkExist(String paramName, String newValue, String oldValue) {
		if (StringUtils.hasText(oldValue) && newValue.equals(oldValue))
			return true;
		return this.findAllEq(paramName, newValue).isEmpty();
	}

	/**
	 * 查询所有实体
	 * @return List 查询结果集
	 */
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return getEntityDao().findAll();
	}

	/**
	 * 根据排序参数查询所有实体
	 * @param sort 排序参数
	 * @return 查询结果集
	 */
	@Transactional(readOnly = true)
	public List<T> findAll(Sort sort) {
		return getEntityDao().findAll(sort);
	}

	/**
	 * 根据ID列表查询所有实体
	 * @param idList ID列表
	 * @return 查询结果集
	 */
	@Transactional(readOnly = true)
	public List<T> findAllById(List<ID> idList) {
		return getEntityDao().findAllById(idList);
	}

	/**
	 * 根据ID数组查询所有实体
	 * @param idArray ID数组
	 * @return 查询结果集
	 */
	@Transactional(readOnly = true)
	public List<T> findAll(ID[] idArray) {
		return findAllById(Arrays.asList(idArray));
	}

	/**
	 * 根据查询条件查询所有实体
	 * @param spec 查询条件
	 * @return 查询结果集
	 */
	@Transactional(readOnly = true)
	public List<T> findAll(Specification<T> spec) {
		return getEntityDao().findAll(spec);
	}

	/**
	 * 根据查询条件查询所有实体
	 * @param <S> 实体类型
	 * @param example 查询条件
	 * @return 查询结果集
	 */
	@Transactional(readOnly = true)
	public <S extends T> List<S> findAll(Example<S> example) {
		return getEntityDao().findAll(example);
	}

	/**
	 * 根据查询条件查询所有实体
	 * @param <S> 实体类型
	 * @param example 查询条件
	 * @param sort 排序参数
	 * @return 查询结果集
	 */
	@Transactional(readOnly = true)
	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		return getEntityDao().findAll(example, sort);
	}

	/**
	 * 根据查询条件查询所有实体
	 * @param spec 查询条件
	 * @param sort 排序条件
	 * @return 查询结果集
	 */
	@Transactional(readOnly = true)
	public List<T> findAll(Specification<T> spec, Sort sort) {
		return getEntityDao().findAll(spec, sort);
	}

	/**
	 * 根据查询条件查询所有实体
	 * @param predicate querydsl查询条件
	 * @return 查询结果集
	 */
	@Transactional(readOnly = true)
	public Iterable<T> findAll(com.querydsl.core.types.Predicate predicate) {
		return getEntityDao().findAll(predicate);
	}

	/**
	 * 根据查询条件查询所有实体
	 * @param predicate querydsl查询条件
	 * @param sort 排序条件
	 * @return 查询结果集
	 */
	@Transactional(readOnly = true)
	public Iterable<T> findAll(com.querydsl.core.types.Predicate predicate, Sort sort) {
		return getEntityDao().findAll(predicate, sort);
	}

	/**
	 * 根据查询条件查询所有实体
	 * @param predicate querydsl查询条件
	 * @param orders 排序条件
	 * @return 查询结果集
	 */
	@Transactional(readOnly = true)
	public Iterable<T> findAll(com.querydsl.core.types.Predicate predicate, OrderSpecifier<?>... orders) {
		return getEntityDao().findAll(predicate, orders);
	}

	/**
	 * 根据排序条件查询所有实体
	 * @param orders 排序条件
	 * @return 查询结果集
	 */
	@Transactional(readOnly = true)
	public Iterable<T> findAll(OrderSpecifier<?>... orders) {
		return getEntityDao().findAll(orders);
	}

	/**
	 * 分页查询所有实体
	 * @param pageable 分页条件
	 * @return Page 分页查询结果,附带结果列表及所有查询时的参数.<br>
	 *         可通过page.getResult()获取.
	 */
	@Transactional(readOnly = true)
	public Page<T> findPage(Pageable pageable) {
		return getEntityDao().findAll(pageable);
	}

	/**
	 * 根据查询条件分页查询
	 * @param pageable 分页条件
	 * @param spec 查询条件
	 * @return Page 分页查询结果,附带结果列表及所有查询时的参数.<br>
	 *         可通过page.getResult()获取.
	 */
	@Transactional(readOnly = true)
	public Page<T> findPage(Pageable pageable, Specification<T> spec) {
		return getEntityDao().findAll(spec, pageable);
	}

	/**
	 * 根据查询条件分页查询
	 * @param pageable 分页条件
	 * @param predicate 查询条件
	 * @return Page 分页查询结果,附带结果列表及所有查询时的参数.<br>
	 *         可通过page.getResult()获取.
	 */
	@Transactional(readOnly = true)
	public Page<T> findPage(Pageable pageable, com.querydsl.core.types.Predicate predicate) {
		return getEntityDao().findAll(predicate, pageable);
	}

	/**
	 * 根据查询条件查询单个对象
	 * @param spec 查询条件
	 * @return 实体对象Optional
	 */
	@Transactional(readOnly = true)
	public Optional<T> findOne(Specification<T> spec) {
		return getEntityDao().findOne(spec);
	}

	/**
	 * 根据Id查询实体<br>
	 * @param predicate querydsl查询条件
	 * @return 实体对象Optional
	 */
	public Optional<T> findOne(com.querydsl.core.types.Predicate predicate) {
		return getEntityDao().findOne(predicate);
	}

	/**
	 * 根据属性查询单个对象
	 * @param param 查询属性
	 * @param value 查询值
	 * @return 单个对象，没有则返回Null
	 */
	@Transactional(readOnly = true)
	public Optional<T> findOneEq(@NonNull String param, @NonNull Object value) {
		return getEntityDao().findOne((root, query, cb) -> cb.equal(root.get(param), value));
	}

	/**
	 * 根据条件精确查询所有实体
	 * @param queryName 要查询的列名
	 * @param queryValue 要查询的值
	 * @return List 查询结果集
	 */
	@Transactional(readOnly = true)
	public List<T> findAllEq(@NonNull String queryName, @NonNull Object queryValue) {
		return getEntityDao().findAll((root, query, cb) -> {
			if (StringUtils.hasText(queryName) && queryValue != null) {
				String[] names = queryName.split("\\.");
				Path<?> expression = root.get(names[0]);
				for (int i = 1; i < names.length; i++) {
					expression = expression.get(names[i]);
				}
				return cb.equal(expression, queryValue);
			}
			return cb.conjunction();
		});
	}

	/**
	 * 根据条件精确分页查询所有实体
	 * @param pageable 分页参数
	 * @param queryName 要查询的列名
	 * @param queryValue 要查询的值
	 * @return List 查询结果集
	 */
	@Transactional(readOnly = true)
	public Page<T> findPageEq(Pageable pageable, @NonNull String queryName, @NonNull Object queryValue) {
		return getEntityDao().findAll((root, query, cb) -> {
			if (StringUtils.hasText(queryName) && queryValue != null) {
				String[] names = queryName.split("\\.");
				Path<?> expression = root.get(names[0]);
				for (int i = 1; i < names.length; i++) {
					expression = expression.get(names[i]);
				}
				return cb.equal(expression, queryValue);
			}
			return cb.conjunction();
		}, pageable);
	}

	/**
	 * 根据查询条件查询结果集
	 * @param queryName 查询条件
	 * @param queryValue 查询值
	 * @return 查询结果列表
	 */
	@Transactional(readOnly = true)
	public List<T> findAllLike(@NonNull String queryName, @NonNull String queryValue) {
		return getEntityDao().findAll((root, query, cb) -> {
			if (StringUtils.hasText(queryValue) && StringUtils.hasText(queryName)) {
				String[] names = queryName.split("\\.");
				Path<String> expression = root.get(names[0]);
				for (int i = 1; i < names.length; i++) {
					expression = expression.get(names[i]);
				}
				return cb.like(expression, "%" + queryValue + "%");
			}
			return cb.conjunction();
		});
	}

	/**
	 * 根据查询条件分页查询结果集
	 * @param pageable 分页参数
	 * @param queryName 查询条件
	 * @param queryValue 查询值
	 * @return 分页列表
	 */
	@Transactional(readOnly = true)
	public Page<T> findPageLike(Pageable pageable, @NonNull String queryName, @NonNull String queryValue) {
		return getEntityDao().findAll((root, query, cb) -> {
			if (StringUtils.hasText(queryValue) && StringUtils.hasText(queryName)) {
				String[] names = queryName.split("\\.");
				Path<String> expression = root.get(names[0]);
				for (int i = 1; i < names.length; i++) {
					expression = expression.get(names[i]);
				}
				return cb.like(expression, "%" + queryValue + "%");
			}
			return cb.conjunction();
		}, pageable);
	}

	/**
	 * 根据查询条件分页查询结果集并排序
	 * @param pageable 分页参数
	 * @param queryName 查询条件
	 * @param queryValue 查询值
	 * @param orderBy 需要排序字段
	 * @param orderDirect 排序方式[desc,asc]
	 * @return 分页列表
	 */
	@Transactional(readOnly = true)
	public Page<T> findPageLikeOrder(Pageable pageable, @NonNull String queryName, @NonNull String queryValue, @NonNull String orderBy, @NonNull String orderDirect) {
		return getEntityDao().findAll((root, query, cb) -> {
			if (StringUtils.hasText(orderBy)) {
				if (StringUtils.hasText(orderDirect)) {
					if (orderDirect.toLowerCase().equals("desc")) {
						query.orderBy(cb.desc(root.get(orderBy)));
					}
					if (orderDirect.toLowerCase().equals("asc")) {
						query.orderBy(cb.asc(root.get(orderBy)));
					}
				}
			}
			if (StringUtils.hasText(queryValue) && StringUtils.hasText(queryName)) {
				String[] names = queryName.split("\\.");
				Path<String> expression = root.get(names[0]);
				for (int i = 1; i < names.length; i++) {
					expression = expression.get(names[i]);
				}
				return cb.like(expression, "%" + queryValue + "%");
			}
			return cb.conjunction();
		}, pageable);
	}

	/**
	 * 根据开始时间和结束时间查询
	 * @param dateName 日期字段
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @return 对象列表
	 */
	@Transactional(readOnly = true)
	public List<T> findAllByDateBetween(@NonNull String dateName, @NonNull Date beginDate, @NonNull Date endDate) {
		return getEntityDao().findAll((root, query, cb) -> {
			Path<?> expression = root.get(dateName);
			Predicate dateWhere = cb.between(expression.as(Date.class), beginDate, endDate);
			query.where(dateWhere);
			query.orderBy(cb.desc(expression.as(Date.class)));
			return query.getRestriction();
		});
	}

	/**
	 * 根据开始时间和结束时间查询
	 * @param pageable 分页参数
	 * @param dateName 日期字段
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @return 对象分页列表
	 */
	@Transactional(readOnly = true)
	public Page<T> findPageByDateBetween(@NonNull Pageable pageable, @NonNull String dateName, @NonNull Date beginDate, @NonNull Date endDate) {
		return getEntityDao().findAll((root, query, cb) -> {
			Path<?> expression = root.get(dateName);
			Predicate dateWhere = cb.between(expression.as(Date.class), beginDate, endDate);
			query.where(dateWhere);
			query.orderBy(cb.desc(expression.as(Date.class)));
			return query.getRestriction();
		}, pageable);
	}

	/**
	 * 按时间范围并且按某属性查询
	 * @param pageable 分页参数
	 * @param dateName 日期字段
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @param objectName 属性名称
	 * @param objectValue 属性值
	 * @return 对象分页列表
	 */
	@Transactional(readOnly = true)
	public Page<T> findPageByDateBetweenAndObject(@NonNull Pageable pageable, @NonNull String dateName, @NonNull Date beginDate, @NonNull Date endDate, @NonNull String objectName, @NonNull Object objectValue) {
		if (beginDate == null || endDate == null) {
			return this.findPageByDateDesc(pageable, dateName);
		}
		return getEntityDao().findAll((root, query, cb) -> {
			Path<?> expression = root.get(dateName);
			Predicate dateWhere = cb.and(cb.between(expression.as(Date.class), beginDate, endDate), cb.equal(root.get(objectName), objectValue));
			query.where(dateWhere);
			return query.getRestriction();
		}, pageable);
	}

	/**
	 * 分页按时间倒序
	 * @param pageable 分页参数
	 * @param dateName 需要排序的日期字段
	 * @return 对象分页列表
	 */
	@Transactional(readOnly = true)
	public Page<T> findPageByDateDesc(@NonNull Pageable pageable, @NonNull String dateName) {
		return getEntityDao().findAll((root, query, cb) -> {
			Path<?> expression = root.get(dateName);
			query.orderBy(cb.desc(expression.as(Date.class)));
			return cb.conjunction();
		}, pageable);
	}

	/**
	 * 分页按时间正序
	 * @param pageable 分页参数
	 * @param dateName 需要排序的日期字段
	 * @return 对象分页列表
	 */
	@Transactional(readOnly = true)
	public Page<T> findPageByDateAsc(@NonNull Pageable pageable, @NonNull String dateName) {
		return getEntityDao().findAll((root, query, cb) -> {
			Path<?> expression = root.get(dateName);
			query.orderBy(cb.asc(expression.as(Date.class)));
			return cb.conjunction();
		}, pageable);
	}

}
