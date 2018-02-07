package com.github.herowzz.springfuse.jpa.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * BaseDao extends spring data interface.<br>
 * Provide data access curd and more method.<br>
 * Your domain dao class can extends this that can use common data access method.
 * @author wangzz
 *
 * @param <T> Model Class Type
 * @param <PK> Model Id key Class Type
 */
@NoRepositoryBean
public interface IBaseDao<T, PK extends Serializable> extends JpaRepository<T, PK>, JpaSpecificationExecutor<T> {

}
