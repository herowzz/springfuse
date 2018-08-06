package com.github.herowzz.springfuse.data.jpa.doc;

public class GenerateDocDirector {

	private GenerateDocBuilder builder;

	public GenerateDocDirector(String packagePath, GenerateDocBuilder builder) {
		this.builder = builder;
		this.builder.loadClass(packagePath);
		this.builder.initConfig();
	}

	public void export(String projectName, String outPath) {
		builder.export(projectName, outPath);
	}

}
