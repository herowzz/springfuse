package com.github.herowzz.springfuse.core.util;

import org.springframework.cglib.beans.BeanCopier;

public abstract class BeanUtils {

	public static <F, T> T copy(F from, Class<T> to) {
		try {
			T t = to.newInstance();
			BeanCopier beanCopier = BeanCopier.create(from.getClass(), t.getClass(), false);
			beanCopier.copy(from, t, null);
			return t;
		} catch (Exception e) {
			return null;
		}
	}

	public static <F, T> T copy(F from, T to) {
		BeanCopier beanCopier = BeanCopier.create(from.getClass(), to.getClass(), false);
		beanCopier.copy(from, to, null);
		return to;
	}

}
