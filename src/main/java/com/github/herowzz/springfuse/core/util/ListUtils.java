package com.github.herowzz.springfuse.core.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * List操作工具类
 * @author wangzz
 */
public abstract class ListUtils {

	public static <O, S> List<S> convert(List<O> originList, Function<? super O, ? extends S> mapper) {
		return originList.stream().map(mapper).collect(Collectors.toList());
	}

}
