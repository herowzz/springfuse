package com.github.herowzz.springfuse.data.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 实体类基类,主键为递增生成方式
 * @author wangzz
 */
@MappedSuperclass
public abstract class BaseIncreaseEntity extends BaseEntity {

	private static final long serialVersionUID = 2676820404664442506L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
