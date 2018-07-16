package com.github.herowzz.springfuse.example.domain.account;

import javax.persistence.Entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.github.herowzz.springfuse.data.domain.BaseUidEntity;

/**
 * 角色
 * @author wangzz
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class Role extends BaseUidEntity {

	private static final long serialVersionUID = 4758971869911775718L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 序号
	 */
	private Integer num;

	/**
	 * 备注
	 */
	private String comments;

	public Role() {
	}

	public Role(String name, Integer num) {
		this.name = name;
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
