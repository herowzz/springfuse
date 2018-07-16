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
 * 操作权限
 * @author wangzz
 */
@Entity(name = "operation_permission")
@DynamicInsert
@DynamicUpdate
public class OperationPermission extends BaseUidEntity {

	private static final long serialVersionUID = 2652015692350504513L;

	/**
	 * 所属功能权限
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "none"))
	private FunctionPermission functionPermission;

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

	public OperationPermission() {
	}

	public OperationPermission(String code, String name, Integer num) {
		this.code = code;
		this.name = name;
		this.num = num;
	}

	public OperationPermission(FunctionPermission functionPermission, String code, String name, Integer num) {
		this.functionPermission = functionPermission;
		this.code = code;
		this.name = name;
		this.num = num;
	}

	public FunctionPermission getFunctionPermission() {
		return functionPermission;
	}

	public void setFunctionPermission(FunctionPermission functionPermission) {
		this.functionPermission = functionPermission;
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
