package com.github.herowzz.springfuse.core.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class SpringFuseGenerator {

	private static Logger logger = LoggerFactory.getLogger(SpringFuseGenerator.class);

	private Configuration cfg = new Configuration(Configuration.getVersion());
	private static Template daoTemp, serviceTemp, controllerTemp, dtoTemp;

	public String entityPackage;
	public String outputPath;

	private List<Class<?>> entityClassList = new ArrayList<>();
	private final int SerialIDType = (Modifier.PRIVATE | Modifier.STATIC | Modifier.FINAL);

	public SpringFuseGenerator(String entityPackage, String outputPath) {
		this.entityPackage = entityPackage;
		this.outputPath = outputPath;

		init();
	}

	public void init() {
		try {
			cfg.setClassForTemplateLoading(this.getClass(), "/generate/");
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

			daoTemp = cfg.getTemplate("dao/dao.ftl");
			serviceTemp = cfg.getTemplate("service/service.ftl");
			controllerTemp = cfg.getTemplate("controller/controller.ftl");
			dtoTemp = cfg.getTemplate("dto/dto.ftl");

			String[] templateArray = { daoTemp.getSourceName(), serviceTemp.getSourceName(), controllerTemp.getSourceName(), dtoTemp.getSourceName() };

			logger.info("加载模板完毕,已加载模板:\n{}\n{}\n{}\n{}\n", templateArray[0], templateArray[1], templateArray[2], templateArray[3]);

			StringBuilder sb = new StringBuilder();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			ImmutableSet<ClassPath.ClassInfo> topLevelClasses = ClassPath.from(classLoader).getTopLevelClassesRecursive(entityPackage);
			for (ClassPath.ClassInfo classInfo : topLevelClasses) {
				Class<?> entityClass = classInfo.load();
				for (Annotation annotation : entityClass.getAnnotations()) {
					if (annotation.annotationType().isAssignableFrom(Entity.class)) {
						entityClassList.add(entityClass);
						sb.append(entityClass.getName()).append("\n");
					}
				}
			}
			logger.info("加载实体类:\n{}", sb.toString());

		} catch (Exception e) {
			logger.error("SpringFuseGenerator初始化模板异常!", e);
			System.exit(1);
		}
	}

	private void exportByType(ExportType exportType, String packageName, String autowiredPackege, String dtoPackage) {
		try {
			File filePath = new File(outputPath);
			if (!filePath.exists() || !filePath.isDirectory()) {
				filePath.mkdirs();
			}
			String outPath = outputPath + "\\" + exportType;
			File daoOutFile = new File(outPath);
			if (!daoOutFile.exists()) {
				daoOutFile.mkdir();
			}
			StringBuilder sb = new StringBuilder();
			for (Class<?> entityClass : entityClassList) {
				String entityName = entityClass.getSimpleName();
				String entitySimpleName = WordUtils.uncapitalize(entityName);
				String entityPackageName = entityClass.getName();
				String entityPackageRoot = entityClass.getPackage().getName();

				Map<String, Object> root = new HashMap<>();
				ReflectionUtils.doWithFields(entityClass, f -> root.put("idType", f.getType().getSimpleName()), field -> field.getAnnotation(Id.class) != null);

				root.put("packageName", packageName);// 传入生成文件的包路径
				root.put("entityName", entityName);// 实体名称(大写)
				root.put("entityPackageName", entityPackageName); // 实体所在包的路径
				root.put("entityPackageRoot", entityPackageRoot); // 实体所在包的根路径
				root.put("entitySimpleName", entitySimpleName); // 实体名称(小写)
				root.put("autowiredPackege", autowiredPackege); // 需要注入的autowired所属包名
				root.put("dtoPackage", dtoPackage);

				List<DtoField> fieldList = new ArrayList<>();
				Set<String> dtoImportSet = new HashSet<>();
				ReflectionUtils.doWithFields(entityClass, f -> {
					Class<?> fType = f.getType();
					DtoField dtoField = new DtoField();
					dtoField.setName(f.getName());
					dtoField.setClassType(fType.getSimpleName());
					boolean isPrimitive = fType.isPrimitive() || fType.getName().indexOf("java.lang") == 0;
					if (!isPrimitive && f.getAnnotation(OneToMany.class) == null && f.getAnnotation(OneToOne.class) == null) {
						if (f.getAnnotation(ManyToOne.class) != null) {
							dtoField.setName(fType.getSimpleName() + "Id");
							dtoField.setClassType(String.class.getSimpleName());
							dtoField.setManyToOne(true);
							dtoField.setManyToOneName(fType.getSimpleName());
						}
						if (fType.getName().indexOf("java.time") == 0) {
							dtoField.setTimeType(fType);
							dtoImportSet.add(JsonFormat.class.getName());
						}
						dtoImportSet.add(fType.getName());
					}
					fieldList.add(dtoField);

				}, field -> field.getModifiers() != SerialIDType);

				root.put("fieldList", fieldList);
				root.put("dtoImportSet", dtoImportSet);

				String newFilePath = outPath + "\\" + entityName + exportType.getTitle() + ".java";
				Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFilePath), "UTF-8"));
				exportType.getTemplate().process(root, out);
				out.flush();
				out.close();
				sb.append(newFilePath).append("\n");
			}
			logger.info("初始化{}文件生成：\n{}", exportType, sb.toString());
		} catch (Exception e) {
			logger.error("SpringFuseGenerator生成" + exportType + "异常!", e);
		}
	}

	/**
	 * 生成dao文件
	 * @param daoPackage dao所在包
	 */
	public void buildDao(String daoPackage) {
		exportByType(ExportType.Dao, daoPackage, "", "");
	}

	/**
	 * 生成Service文件
	 * @param servicePackage service所在包
	 * @param daoPackage dao所在包
	 */
	public void buildService(String servicePackage, String daoPackage) {
		exportByType(ExportType.Service, servicePackage, daoPackage, "");
	}

	/**
	 * 生成Dto文件
	 * @param dtoPackage dto所在包
	 */
	public void buildDto(String dtoPackage) {
		exportByType(ExportType.Dto, dtoPackage, "", "");
	}

	/**
	 * 生成Controller文件
	 * @param controllerPackage controller所在包
	 * @param servicePackage service所在包
	 * @param dtoPackage dto所在包
	 */
	public void buildController(String controllerPackage, String servicePackage, String dtoPackage) {
		exportByType(ExportType.Controller, controllerPackage, servicePackage, dtoPackage);
	}

	/**
	 * 生成所有文件
	 * @param packageName dao,service,controller所在包的上层
	 */
	public void buildAll(String packageName) {
		String daoPackage = packageName + ".dao";
		String servicePackage = packageName + ".service";
		String dtoPackage = packageName + ".dto";
		buildDao(daoPackage);
		buildService(servicePackage, daoPackage);
		buildDto(dtoPackage);
		buildController(packageName + ".controller", servicePackage, dtoPackage);
	}

	enum ExportType {
		Dao, Service, Dto, Controller;

		public String getTitle() {
			return this.toString();
		}

		public Template getTemplate() {
			switch (this) {
			case Dao:
				return daoTemp;
			case Service:
				return serviceTemp;
			case Dto:
				return dtoTemp;
			case Controller:
				return controllerTemp;
			default:
				return null;
			}
		}
	}
}
