package com.github.herowzz.springfuse.doc.db.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import com.github.herowzz.springfuse.core.util.StringUtils;
import com.github.herowzz.springfuse.data.domain.annotation.Comment;

public class DbTable {

	private String name = "";

	private String desc = "";

	private String className = "";

	private List<DbParam> paramList = new ArrayList<>();

	public void addDbParam(DbParam dbParam) {
		if (dbParam.isId())
			this.paramList.add(0, dbParam);
		else
			this.paramList.add(dbParam);
	}

	public static DbTable build(Class<?> c) {
		DbTable tb = new DbTable();
		tb.name = StringUtils.camel2Underscore(c.getSimpleName());
		tb.className = c.getName();
		Entity entity = c.getAnnotation(Entity.class);
		if (entity != null && !entity.name().equals("")) {
			tb.name = entity.name();
		}
		Comment commentAnno = c.getAnnotation(Comment.class);
		if (commentAnno != null) {
			tb.desc = commentAnno.value();
		}
		return tb;
	}

	public String getFullName() {
		if ("".equals(this.desc))
			return this.name;
		else
			return this.name + " (" + this.desc + ")";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DbTable [name=");
		builder.append(name);
		builder.append(", desc=");
		builder.append(desc);
		builder.append(", className=");
		builder.append(className);
		builder.append("]");
		return builder.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<DbParam> getParamList() {
		return paramList;
	}

	public void setParamList(List<DbParam> paramList) {
		this.paramList = paramList;
	}

}
