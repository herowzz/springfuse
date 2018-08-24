package com.github.herowzz.springfuse.example.generator;

import com.github.herowzz.springfuse.doc.db.GenerateDocDirector;
import com.github.herowzz.springfuse.doc.db.impl.GenerateHtmlDbDoc;
import com.github.herowzz.springfuse.doc.db.impl.GeneratePdfDbDoc;
import com.github.herowzz.springfuse.doc.db.impl.GenerateWordDbDoc;

public class TestGenerateDbDoc {

	static String EntityBasePackage = "com.github.herowzz.springfuse.example.domain";

	public static void main(String[] args) throws Exception {

		GenerateDocDirector director = new GenerateDocDirector(EntityBasePackage, new GenerateHtmlDbDoc());
		director.export("SpringFuse", "E:\\temp");

		director = new GenerateDocDirector(EntityBasePackage, new GenerateWordDbDoc());
		director.export("SpringFuse", "E:\\temp");

		director = new GenerateDocDirector(EntityBasePackage, new GeneratePdfDbDoc());
		director.export("SpringFuse", "E:\\temp");

	}

}