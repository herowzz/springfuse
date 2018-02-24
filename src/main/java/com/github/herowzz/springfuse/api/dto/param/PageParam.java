package com.github.herowzz.springfuse.api.dto.param;

import javax.validation.constraints.Positive;

/**
 * 分页请求参数
 * @author wangzz
 */
public class PageParam {

	/**
	 * 当前第几页
	 */
	@Positive
	public int pageNo = 1;

	/**
	 * 一页多少条
	 */
	@Positive
	public int pageSize = 10;

	public PageParam() {
	}

	public PageParam(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo - 1;
	}

	public int getPageSize() {
		if (pageSize > 1000)
			pageSize = 1000;
		return pageSize;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PageParam [pageNo=");
		builder.append(pageNo);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append("]");
		return builder.toString();
	}

}
