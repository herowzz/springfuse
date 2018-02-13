package com.github.herowzz.springfuse.api.dto.param;

/**
 * 分页请求参数
 * @author wangzz
 */
public class PageParam {

	/**
	 * 当前第几页
	 */
	public int pageNo = 1;

	/**
	 * 一页多少条
	 */
	public int pageSize = 10;

	public PageParam() {
	}

	public PageParam(int pageSize) {
		this.pageSize = pageSize;
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
