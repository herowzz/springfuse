package com.github.herowzz.springfuse.data.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.github.herowzz.springfuse.data.domain.annotation.Comment;

/**
 * 实体类基类,主键为自定义,需手动生成主键
 * @author wangzz
 */
@MappedSuperclass
public abstract class BaseCustomIdEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Comment("主键")
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
