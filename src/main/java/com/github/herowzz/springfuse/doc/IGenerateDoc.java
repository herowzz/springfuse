package com.github.herowzz.springfuse.doc;

public interface IGenerateDoc {

	/**
	 * 导出文档
	 * @param projectName 项目名称
	 * @param exportPath 导出路径
	 * @throws Exception
	 */
	void export(String projectName, String exportPath);

}
