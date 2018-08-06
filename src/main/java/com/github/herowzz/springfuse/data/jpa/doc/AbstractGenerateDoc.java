package com.github.herowzz.springfuse.data.jpa.doc;

import java.beans.Transient;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.persistence.Entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.github.herowzz.springfuse.core.util.ReflectUtils;
import com.github.herowzz.springfuse.data.jpa.doc.vo.DbParam;
import com.github.herowzz.springfuse.data.jpa.doc.vo.DbTable;

public abstract class AbstractGenerateDoc extends GenerateDocBuilder {

	public static final Logger logger = LoggerFactory.getLogger(AbstractGenerateDoc.class);

	protected Map<String, List<DbTable>> tbMap = new TreeMap<>();

	@Override
	public void loadClass(String entityBasePackage) {
		try {
			this.tbMap = this.getDbCharKeyMap(entityBasePackage);
			StringBuilder sb = new StringBuilder();
			tbMap.forEach((k, v) -> v.stream().forEach(c -> sb.append(c.getClassName()).append("\n")));
			logger.info("加载实体类:\n{}", sb.toString());
		} catch (Exception e) {
			logger.error("加载实体类异常!", e);
			System.exit(1);
		}
	}

	/**
	 * 获取实体对象Map并以首字母为key
	 * @param entityBasePackage 实体所在包
	 * @return map
	 * @throws Exception
	 */
	public Map<String, List<DbTable>> getDbCharKeyMap(String entityBasePackage) throws Exception {
		Map<String, List<DbTable>> tbMap = new TreeMap<>();
		List<Class<?>> entityClassList = ReflectUtils.getClassListByBasePackage(entityBasePackage, Entity.class);
		Map<String, List<Class<?>>> charMap = ReflectUtils.convertCharKeyMap(entityClassList);
		for (Entry<String, List<Class<?>>> entry : charMap.entrySet()) {
			List<DbTable> tbList = new ArrayList<>();
			entry.getValue().stream().forEach(c -> {
				DbTable tb = DbTable.build(c);
				ReflectionUtils.doWithFields(c, f -> {
					if (!Modifier.isStatic(f.getModifiers()) && !f.isAnnotationPresent(Transient.class)) {
						tb.addDbParam(DbParam.build(f));
					}
				});
				tbList.add(tb);
			});
			tbMap.put(entry.getKey(), tbList);
		}
		return tbMap;
	}

}
