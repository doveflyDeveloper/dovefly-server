package cn.dovefly.maven.plugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.dovefly.maven.plugin.vo.Column;
import cn.dovefly.maven.plugin.vo.Project;
import cn.dovefly.maven.plugin.vo.Table;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * freemarker 模板工具
 * 
 * @author Ying-er
 * @time 2010-2-6下午04:07:27
 * @version 1.0
 */
public class FreeMarkerUtil {

	public static String ENCODING = "UTF-8";

	/**
	 * 创建Configuration对象
	 * 
	 * @param templatePath
	 *			模板文件基目录
	 * @return
	 * @throws IOException
	 */
	public static Configuration getConfiguration(String templatePath) {
		// 创建Configuration对象
		Configuration config = new Configuration();

		// 指定模板路径目录，并加载模板文件
		try {
			config.setDirectoryForTemplateLoading(new File(templatePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 设置包装器，并将对象包装为数据模型
		config.setObjectWrapper(new DefaultObjectWrapper());

		return config;
	}

	/**
	 * 获取模板基目录下所有模板文件
	 * 
	 * @param templatePath
	 * @return
	 */
	public static List<String> getfilesPath(String templatePath) {
		List<String> files = new ArrayList<String>();
		inerateDir(templatePath, files, ".ftl");
		return files;
	}

	/**
	 * 迭代目录结果搜索所有的指定后缀名的文件
	 * 
	 * @param path
	 */
	public static void inerateDir(String path, List<String> filesPath, String endsWithStr) {
		File file = new File(path);
		if (file.exists()) {
			if (file.isFile()) {
				if (file.getName().endsWith(endsWithStr)) {
					filesPath.add(file.getPath().replaceAll("\\\\", "/"));
				}
			} else {
				File[] tmp = file.listFiles();
				if (tmp != null) {
					for (int i = 0; i < tmp.length; i++) {
						inerateDir(tmp[i].getPath(), filesPath, endsWithStr);
					}
				}
			}
		}
	}
	
	public static void generateFiles(Project project, Table table) {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("project", project);
		root.put("table", table);

		// 创建Configuration对象
		String templateDir = FreeMarkerUtil.class.getResource("").getPath() + "templates/singleTableStandard";
		Configuration config = getConfiguration(templateDir);
		List<String> filesPath = getfilesPath(templateDir);
		for (String filePath : filesPath) {
			analysisTemplate(config, filePath, root);
		}
	}

	/**
	 * 
	 * @param templateName
	 *			模板文件名称
	 * @param root
	 *			数据模型根对象
	 */
	public static void analysisTemplate(Configuration config,
			String templateName, Map<?, ?> root) {
		try {
			// 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			String subTemplateName = templateName.substring(templateName
					.indexOf("singleTableStandard")
					+ "singleTableStandard".length());
			Template template = config.getTemplate(subTemplateName, ENCODING);

			// 合并数据模型与模板
			Table table = (Table) root.get("table");
			Project project = (Project) root.get("project");
			String suffix = templateName.indexOf("java") >= 0 ? ".java" : ".jsp";
			table.getUpperFirstTableFormatName();
			String filePath = "";
			if(suffix.equals(".java")){
				filePath = project.getBaseProjectPath() + "/"
				+ project.getJavaFileRealPath() 
				+ subTemplateName
						.replace("java", table.getLowerAllTableFormatName())
						.replace("TableName", table.getUpperFirstTableFormatName())
						.replace(".ftl", suffix);
			}else{
				filePath = project.getBaseProjectPath() + "/"
				+ project.getWebAppName() + "/" + project.getJspSourcePath()
				+ subTemplateName
						.replace("jsp", table.getLowerAllTableFormatName())
						.replace("TableName", table.getUpperFirstTableFormatName())
						.replace(".ftl", suffix);
			}

			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			Writer out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			// Writer out = new OutputStreamWriter(System.out);//测试查看结果
			template.process(root, out);
			out.flush();
			out.close();
			System.out.println("模板文件：" + templateName);
			System.out.println("生成文件：" + filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//generateFiles("t_partner_mod_base_info");
		
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			//Configuration configuration = new Configuration();
			String templateDir = FreeMarkerUtil.class.getResource("").getPath() + "templates/singleTableStandard";
			Configuration configuration = getConfiguration(templateDir);
			configuration.setDefaultEncoding("UTF-8");// 这里很重要
			dataMap.put("name", "格格");
			dataMap.put("age", 11);
			Template t = null;
			try {
				// test.ftl为要装载的模板
				t = configuration.getTemplate("excel2003.xml");
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 输出文档路径及名称
			File outFile = new File("D:/excel2003.xls");
			Writer out = null;
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));// 这里很重要
			t.process(dataMap, out);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
}
