package com.github.herowzz.springfuse.data.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.github.herowzz.springfuse.data.domain.annotation.Comment;

/**
 * 实体类基类,主键为UUID生成方式
 * 
 * @author wangzz
 */
@MappedSuperclass
public abstract class BaseUidEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	@Comment("主键,UUID")
	@Column(length = 50)
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
