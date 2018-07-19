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
import com.github.herowzz.springfuse.example.domain.refrence.PermissionTypeEnum;

/**
 * 权限
 * @author wangzz
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class Permission extends BaseUidEntity {

	private static final long serialVersionUID = 1812548452397582331L;

	/**
	 * 父级权限
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "none"))
	private Permission parent;

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
	 * 权限类型
	 */
	private PermissionTypeEnum type;

	/**
	 * 备注
	 */
	private String comments;

	public Permission() {
	}

	public Permission(Permission parent, String code, String name, Integer num, PermissionTypeEnum type) {
		this.parent = parent;
		this.code = code;
		this.name = name;
		this.num = num;
		this.type = type;
	}

	public String getParentId() {
		String parentId = "";
		if (parent != null)
			parentId = parent.getId();
		return parentId;
	}

	public Permission getParent() {
		return parent;
	}

	public void setParent(Permission parent) {
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

	public PermissionTypeEnum getType() {
		return type;
	}

	public void setType(PermissionTypeEnum type) {
		this.type = type;
	}

}
