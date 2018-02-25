package com.github.herowzz.springfuse.data.jpa.dao;

import java.io.Serializable;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

/**
 * BaseDao extends spring data interface.<br>
 * Provide data access curd and more method.<br>
 * Your domain dao interface can extends this that can use common data access method.
 * @author wangzz
 *
 * @param <T> Model Class Type
 * @param <PK> Model Id key Class Type
 */
@NoRepositoryBean
public interface IBaseDao<T, PK extends Serializable> extends JpaRepository<T, PK>, JpaSpecificationExecutor<T> {

	/**
	 * 根据主键带锁查询实体(for update)<br>
	 * service层调用时需加上{@link javax.transaction.Transactional}注解,事物结束之后将自动释放锁
	 * @param ID 主键
	 * @return 实体(没有则返回null)
	 */
	@Lock(value = LockModeType.PESSIMISTIC_WRITE)
	@Query("select t from #{#entityName} t where t.id = :ID")
	public T findByIdForUpdate(@Param("ID") PK ID);

}
