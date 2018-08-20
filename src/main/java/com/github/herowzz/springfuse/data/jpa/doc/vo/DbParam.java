package com.github.herowzz.springfuse.data.jpa.doc.vo;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.util.ReflectionUtils;

import com.github.herowzz.springfuse.core.bean.enumtype.IBaseEnum;
import com.github.herowzz.springfuse.core.util.StringUtils;
import com.github.herowzz.springfuse.data.domain.annotation.Comment;

public class DbParam {

	private boolean isId = false;

	private String name = "";

	private String type = "";

	private String content = "";

	private String desc = "";

	private String remark = "";

	private boolean isManyToOne = false;

	private String className = "";

	public static DbParam build(Field f) {
		DbParam param = new DbParam();
		Class<?> fieldType = f.getType();
		if (f.isAnnotationPresent(Id.class)) {
			param.isId = true;
		}
		param.className = fieldType.getName();
		param.name = StringUtils.camel2Underscore(f.getName());
		Column column = f.getAnnotation(Column.class);
		if (column != null && !"".equals(column.name())) {
			param.name = column.name();
		}
		param.type = DocFieldType.getFieldType(fieldType);
		Comment comment = f.getAnnotation(Comment.class);
		if (comment != null) {
			param.content = comment.value();
			param.desc = comment.desc();
			param.remark = comment.remark();
		}
		if (fieldType.isEnum()) {
			param.type = int.class.getSimpleName();
			String enumDesc = "";
			for (Object obj : fieldType.getEnumConstants()) {
				if (obj instanceof IBaseEnum) {
					IBaseEnum oEnum = (IBaseEnum) obj;
					enumDesc += oEnum.getValue() + "：" + oEnum.getTitle() + ";  ";
				}
			}
			param.desc = enumDesc;
		}
		ManyToOne manyToOne = f.getAnnotation(ManyToOne.class);
		if (manyToOne != null) {
			param.isManyToOne = true;
			ReflectionUtils.doWithFields(fieldType, fp -> {
				if (fp.isAnnotationPresent(Id.class)) {
					param.name += "_id";
					param.content += "ID";
					param.type = DocFieldType.getFieldType(fp.getType());
				}
			});
			String remarkRel = "外键关联";
			Entity relaEntity = fieldType.getAnnotation(Entity.class);
			if (relaEntity != null && !"".equals(relaEntity.name())) {
				remarkRel += relaEntity.name();
			} else {
				remarkRel += StringUtils.camel2Underscore(fieldType.getSimpleName());
			}
			param.remark = remarkRel;
		}
		return param;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DbParam [isId=");
		builder.append(isId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", content=");
		builder.append(content);
		builder.append(", desc=");
		builder.append(desc);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", isManyToOne=");
		builder.append(isManyToOne);
		builder.append(", className=");
		builder.append(className);
		builder.append("]");
		return builder.toString();
	}

	public boolean isId() {
		return isId;
	}

	public void setId(boolean isId) {
		this.isId = isId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isManyToOne() {
		return isManyToOne;
	}

	public void setManyToOne(boolean isManyToOne) {
		this.isManyToOne = isManyToOne;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
