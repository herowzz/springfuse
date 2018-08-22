package com.github.herowzz.springfuse.example.generator;

import com.github.herowzz.springfuse.data.jpa.doc.GenerateDocDirector;
import com.github.herowzz.springfuse.data.jpa.doc.impl.GenerateHtmlDoc;
import com.github.herowzz.springfuse.data.jpa.doc.impl.GeneratePdfDoc;
import com.github.herowzz.springfuse.data.jpa.doc.impl.GenerateWordDoc;

public class TestGenerateDoc {

	static String EntityBasePackage = "com.github.herowzz.springfuse.example.domain";

	public static void main(String[] args) throws Exception {

		GenerateDocDirector director = new GenerateDocDirector(EntityBasePackage, new GenerateHtmlDoc());
		director.export("SpringFuse", "E:\\temp");

		director = new GenerateDocDirector(EntityBasePackage, new GenerateWordDoc());
		director.export("SpringFuse", "E:\\temp");

		director = new GenerateDocDirector(EntityBasePackage, new GeneratePdfDoc());
		director.export("SpringFuse", "E:\\temp");

	}

}