package com.github.herowzz.springfuse.data.jpa.doc;

public abstract class GenerateDocBuilder implements IGenerateDoc {

	public abstract void initConfig();

	public abstract void loadClass(String entityBasePackage);

	public abstract void export(String projectName, String outPath);

}
