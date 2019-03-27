package com.github.herowzz.springfuse.data.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.github.herowzz.springfuse.data.domain.annotation.Comment;

/**
 * 实体类基类,主键为日期生成方式
 * @author wangzz
 */
@MappedSuperclass
public abstract class BaseDateTimeIdEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "dateTimeId")
	@GenericGenerator(name = "dateTimeId", strategy = "com.github.herowzz.springfuse.data.jpa.generator.DateTimeIdGenerator")
	@Comment("主键")
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
