package com.github.herowzz.springfuse.data.jpa.doc.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.github.herowzz.springfuse.data.jpa.doc.AbstractGenerateDoc;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class GeneratePdfDoc extends AbstractGenerateDoc {

	private Configuration cfg = new Configuration(Configuration.getVersion());
	private Template htmlTemp;

	@Override
	public void initConfig() {
		try {
			cfg.setClassForTemplateLoading(GeneratePdfDoc.class, "/generate/");
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			htmlTemp = cfg.getTemplate("doc/pdf.ftl");
			logger.info("加载模板：{}", "doc/pdf.ftl");
		} catch (Exception e) {
			logger.error("GeneratePdfDoc初始化模板异常!", e);
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

			StringWriter stringWriter = new StringWriter();
			htmlTemp.process(root, stringWriter);
			String result = stringWriter.toString();

			String newFilePath = exportPath + "\\dbDoc.pdf";
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(newFilePath));
			document.open();
			XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
			worker.parseXHtml(writer, document, new ByteArrayInputStream(result.getBytes("UTF-8")), Charset.forName("UTF-8"), new AsianFontProvider());
			document.close();

			logger.info("DB文件生成：{}", newFilePath);
		} catch (Exception e) {
			logger.error("GeneratePdfDoc生成pdf异常!", e);
		}
	}

	public class AsianFontProvider extends XMLWorkerFontProvider {

		@Override
		public Font getFont(final String fontname, String encoding, float size, final int style) {
			String fntname = fontname;
			if (fntname == null) {
				fntname = "宋体";
			}
			return super.getFont(fntname, encoding, size, style);
		}
	}

}
