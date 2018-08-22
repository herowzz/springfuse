package com.github.herowzz.springfuse.doc.db;

public class GenerateDocDirector {

	private GenerateDbDocBuilder builder;

	public GenerateDocDirector(String packagePath, GenerateDbDocBuilder builder) {
		this.builder = builder;
		this.builder.loadClass(packagePath);
		this.builder.initConfig();
	}

	public void export(String projectName, String outPath) {
		builder.export(projectName, outPath);
	}

}
