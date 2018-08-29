package com.github.herowzz.springfuse.doc.db;

import com.github.herowzz.springfuse.doc.IGenerateDoc;

public abstract class GenerateDbDocBuilder implements IGenerateDoc {

	public abstract void initConfig();

	public abstract void loadClass(String entityBasePackage);

}
