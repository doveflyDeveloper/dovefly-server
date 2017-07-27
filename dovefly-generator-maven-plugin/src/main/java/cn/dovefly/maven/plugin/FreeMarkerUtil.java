package cn.dovefly.maven.plugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.dovefly.maven.plugin.util.DBHelper;
import cn.dovefly.maven.plugin.vo.Pojo;
import cn.dovefly.maven.plugin.vo.DbTable;

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
//
//	/**
//	 * 获取模板基目录下所有模板文件
//	 *
//	 * @param templatePath
//	 * @return
//	 */
//	public static List<String> getfilesPath(String templatePath) {
//		List<String> files = new ArrayList<>();
//		inerateDir(templatePath, files, ".ftl");
//		return files;
//	}
//
//	/**
//	 * 迭代目录结果搜索所有的指定后缀名的文件
//	 *
//	 * @param path
//	 */
//	public static void inerateDir(String path, List<String> filesPath, String endsWithStr) {
//		File file = new File(path);
//		if (file.exists()) {
//			if (file.isFile()) {
//				if (file.getName().endsWith(endsWithStr)) {
//					filesPath.add(file.getPath().replaceAll("\\\\", "/"));
//				}
//			} else {
//				File[] tmp = file.listFiles();
//				if (tmp != null) {
//					for (int i = 0; i < tmp.length; i++) {
//						inerateDir(tmp[i].getPath(), filesPath, endsWithStr);
//					}
//				}
//			}
//		}
//	}
	
	public static void generateFiles(DbTable table) {
		try {
			if (table == null) {
				return;
			}
			if (table.getPojos() == null || table.getPojos().size() == 0) {
				return;
			}

			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("table", table);

			String templateDir = FreeMarkerUtil.class.getResource("/").getPath() + "templates/";
			Configuration configuration = getConfiguration(templateDir);
			configuration.setDefaultEncoding(ENCODING);

			for (Pojo pojo : table.getPojos().values()) {

				Template template = configuration.getTemplate(pojo.getTemplateFile(), ENCODING);
				// 输出文档路径及名称
				String destFilePath = pojo.getTargetProject() + "/" + pojo.getClassName() + "." + pojo.getFileType();
				File outFile = new File(destFilePath);
				if (!outFile.getParentFile().exists()) {
					outFile.getParentFile().mkdirs();
				}

				Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), ENCODING));
				template.process(dataMap, out);
				out.flush();
				out.close();
				System.out.println("模板文件：" + pojo.getTemplateFile() + ",生成文件：" + destFilePath);
			}

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

	public static void main(String[] args) {
		try {
			DbTable table = new DBHelper().getTable("test_employee");
			generateFiles(table);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
