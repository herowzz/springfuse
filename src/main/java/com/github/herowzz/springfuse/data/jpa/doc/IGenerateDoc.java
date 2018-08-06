package com.github.herowzz.springfuse.data.jpa.doc;

public interface IGenerateDoc {

	/**
	 * 导出数据文档
	 * @param projectName 项目名称
	 * @param exportPath 导出路径
	 * @throws Exception
	 */
	void export(String projectName, String exportPath);

}
