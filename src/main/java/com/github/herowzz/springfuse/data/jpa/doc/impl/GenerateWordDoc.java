package com.github.herowzz.springfuse.data.jpa.doc.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.github.herowzz.springfuse.data.jpa.doc.AbstractGenerateDoc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class GenerateWordDoc extends AbstractGenerateDoc {

	private Configuration cfg = new Configuration(Configuration.getVersion());
	private Template htmlTemp;

	@Override
	public void initConfig() {
		try {
			cfg.setClassForTemplateLoading(GenerateHtmlDoc.class, "/generate/");
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			htmlTemp = cfg.getTemplate("doc/word.ftl");
			logger.info("加载模板：{}", "doc/word.ftl");
		} catch (Exception e) {
			logger.error("GenerateWordDoc初始化模板异常!", e);
			System.exit(1);
		}
	}

	@Override
	public void export(String projectName, String exportPath) {
		try {
			File filePath = new File(exportPath);
			if (!filePath.exists() || !filePath.isDirectory()) {
				filePath.mkdirs();
			}

			Map<String, Object> root = new HashMap<>();
			root.put("projectName", projectName);
			root.put("tbMap", super.tbMap);

			String newFilePath = exportPath + "\\dbDoc.doc";
			try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFilePath), "UTF-8"))) {
				htmlTemp.process(root, out);
				out.flush();
			}
			logger.info("DB文件生成：{}", newFilePath);
		} catch (Exception e) {
			logger.error("GenerateWordDoc生成word异常!", e);
		}
	}

}
