package com.asi.mechanism;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	private static Logger log = LogManager.getLogger(Swagger2Config.class);

	@Value("${environments.project.name}")
	private String projectname;

	@Bean
	public Docket api() throws Exception {
		// XXX 測試失敗，取不到annotation內的資料
		// 取得Lancher的annotation，設定至docket
//		List<String> initialClasses = this.findClassesPackageStrByPackage("com.asi.huanan.initial", "^Launch");
//		System.out.println("size:" + initialClasses.size());
//		Object obj = initialClasses.get(0);
//		Class<? extends Object> clazz = Class.forName(initialClasses.get(0));
//		System.out.println("size:" + clazz.getName());
//		List<Annotation> annoList = Arrays.asList(clazz.getAnnotations());
//		Annotation component = annoList.stream().filter(c -> c.annotationType().getName().contains("ComponentScan"))
//				.collect(Collectors.toList()).get(0);// Spring initial此專案設計一定要有

		String[] packs = new String[] { "com.asi.huanan.controller", "com.asi.huanan.controller.api",
				"com.asi.mechanism.controller" };
		Docket docket = new Docket(DocumentationType.SWAGGER_2);

		ApiSelectorBuilder sel = docket.select();
//		Arrays.asList(packs).stream().forEach(p -> sel.apis(RequestHandlerSelectors.basePackage(p)));

		sel.apis(RequestHandlerSelectors.any());
		sel.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class));
//		sel.apis(RequestHandlerSelectors.basePackage("com.asi.mechanism.controller"));

		sel.paths(PathSelectors.regex("/.*")).build().apiInfo(this.apiEndPointsInfo());

		return docket;

//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.asi.huanan.controller.api"))// scan api of package
//				.paths(PathSelectors.regex("/.*")).build().apiInfo(this.apiEndPointsInfo());
	}

	/**
	 * 
	 * @return
	 */
	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title(this.projectname + "-" + "_RESTFul_API").description(this.projectname)
				.contact(
						new Contact(SpringProperty.getEnvironmentsProjectName(),
								SpringProperty.getThisProjectDomain() + "/"
										+ SpringProperty.getEnvironmentsProjectName() + "/login",
								""))
				.version("1.0.0").build();
//				.license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0")
//				.build();
	}

	/**
	 * 找到package中class
	 * 
	 * @param folder
	 * @return
	 * @throws Exception
	 */
	public List<String> findClassesPackageStrByPackage(String packageName, String regex) throws Exception {
		List<File> resList2 = this.fetchElementFromPackage(packageName);
		File folder = resList2.get(0);// 理論上僅一筆資料夾

		List<File> list = this.findMathRegexFilesInFolder(folder, regex);

		List<String> reslist = list.stream().map(f -> {
			String tmp = f.getName();
			int cutPosi = tmp.lastIndexOf(".");// 通常會含副檔名，故去除
			String classNm = tmp.substring(0, cutPosi);

			String classPackage = packageName + "." + classNm;
			return classPackage;
		}).filter(res -> res != null).collect(Collectors.toList());

		return reslist;
	}

	/**
	 * 取得package的實體路徑
	 * 
	 * @param packageName
	 * @return
	 * @throws IOException
	 */
	public List<File> fetchElementFromPackage(String packageName) throws IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);

		List<File> dirs = new ArrayList<>();

		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}

		return dirs;
	}

	/**
	 * 
	 * @param folder
	 * @param regex
	 * @return
	 * @throws Exception
	 */
	public List<File> findMathRegexFilesInFolder(File folder, String regex) throws Exception {
		if (folder.exists() == false || folder.isDirectory() == false) {
			throw new Exception("input folder param must be folder");
		}

		File[] files = folder.listFiles();

		return Arrays.asList(files).stream().filter(file -> this.regularExp(file.getName(), regex))
				.collect(Collectors.toList());
	}

	/**
	 * 判斷字串是否符合正規表示式格式
	 * 
	 * @param str        欲判斷之字串
	 * @param compileStr 正規表示式字串
	 * 
	 * 
	 */
	public boolean regularExp(String str, String compileStr) {
		Pattern patternCompie = Pattern.compile(compileStr);
		Matcher pattern = patternCompie.matcher(str);
		return pattern.find();
	}
}
