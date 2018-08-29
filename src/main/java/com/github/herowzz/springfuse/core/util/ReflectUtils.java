package com.github.herowzz.springfuse.core.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

public abstract class ReflectUtils {

	/**
	 * 获取给定包下的所有类(包括子包)
	 * @param basePackage 需要获取类的所在包
	 * @param hasAnnotation 根据Annotation筛选
	 * @return 包下的所有类组成List
	 * @throws IOException if the attempt to read class path resources (jar files or directories)failed.
	 */
	@SafeVarargs
	public static List<Class<?>> getClassListByBasePackage(String basePackage, Class<? extends Annotation>... hasAnnotation) throws IOException {
		List<Class<?>> entityClassList = new ArrayList<>();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		ImmutableSet<ClassPath.ClassInfo> topLevelClasses = ClassPath.from(classLoader).getTopLevelClassesRecursive(basePackage);
		for (ClassPath.ClassInfo classInfo : topLevelClasses) {
			Class<?> entityClass = classInfo.load();
			for (Class<? extends Annotation> annotation : hasAnnotation) {
				if (entityClass.isAnnotationPresent(annotation)) {
					entityClassList.add(entityClass);
				}
			}
		}
		return entityClassList;
	}

	/**
	 * 将给定类的list转化为首字母为key的map
	 * @param classList 类list
	 * @return map<br>
	 *         key为首字母大写,value为该首字母下的类list
	 */
	public static Map<String, List<Class<?>>> convertCharKeyMap(List<Class<?>> classList) {
		return classList.stream().collect(Collectors.groupingBy(c -> StringUtils.left(c.getSimpleName(), 1)));
	}

}
