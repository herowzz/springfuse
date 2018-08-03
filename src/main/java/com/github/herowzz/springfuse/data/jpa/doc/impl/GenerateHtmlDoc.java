package com.github.herowzz.springfuse.data.jpa.doc.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.github.herowzz.springfuse.data.jpa.doc.IGenerateDoc;
import com.github.herowzz.springfuse.data.jpa.doc.vo.DbTable;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class GenerateHtmlDoc implements IGenerateDoc {

	private Configuration cfg = new Configuration(Configuration.getVersion());
	private Template htmlTemp;
	private Map<String, List<DbTable>> tbMap = new TreeMap<>();

	public GenerateHtmlDoc(String entityBasePackage) {
		init(entityBasePackage);
	}

	public void init(String entityBasePackage) {
		try {
			cfg.setClassForTemplateLoading(GenerateHtmlDoc.class, "/generate/");
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			htmlTemp = cfg.getTemplate("doc/html.ftl");
			tbMap = this.getDbCharKeyMap(entityBasePackage);
			StringBuilder sb = new StringBuilder();
			tbMap.forEach((k, v) -> v.stream().forEach(c -> sb.append(c.getClassName()).append("\n")));
			logger.info("加载实体类:\n{}", sb.toString());
		} catch (Exception e) {
			logger.error("GenerateHtmlDoc初始化模板异常!", e);
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
			root.put("tbMap", tbMap);

			String newFilePath = exportPath + "\\dbDoc.html";
			try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFilePath), "UTF-8"))) {
				htmlTemp.process(root, out);
				out.flush();
			}
			logger.info("DB文件生成：{}", newFilePath);
		} catch (Exception e) {
			logger.error("GenerateHtmlDoc生成html异常!", e);
		}
	}

}
