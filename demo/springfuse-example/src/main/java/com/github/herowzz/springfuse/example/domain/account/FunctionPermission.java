package com.github.herowzz.springfuse.example.domain.account;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.github.herowzz.springfuse.data.domain.BaseUidEntity;

/**
 * 功能权限
 * @author wangzz
 */
@Entity(name = "function_permission")
@DynamicInsert
@DynamicUpdate
public class FunctionPermission extends BaseUidEntity {

	private static final long serialVersionUID = -1811543530179200527L;

	/**
	 * 父级权限
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "none"))
	private FunctionPermission parent;

	/**
	 * 权限码
	 */
	private String code;

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

	public FunctionPermission() {
	}

	public FunctionPermission(String code, String name, Integer num) {
		this.code = code;
		this.name = name;
		this.num = num;
	}

	public FunctionPermission(FunctionPermission parent, String code, String name, Integer num) {
		this.parent = parent;
		this.code = code;
		this.name = name;
		this.num = num;
	}

	public FunctionPermission getParent() {
		return parent;
	}

	public void setParent(FunctionPermission parent) {
		this.parent = parent;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
