package com.github.herowzz.springfuse.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class NumberUtils {

	public static BigDecimal getRelatRate(BigDecimal benchmark, BigDecimal value) {
		if (benchmark == null || value == null)
			return null;
		double diff = benchmark.subtract(value).doubleValue();
		if (diff == 0) {
			return BigDecimal.valueOf(100);
		} else if (diff > 0) {
			return value.divide(benchmark, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
		} else { // diff < 0
			return value.subtract(benchmark).divide(benchmark, 4, RoundingMode.HALF_DOWN).add(BigDecimal.ONE).multiply(BigDecimal.valueOf(100));
		}
	}

}
