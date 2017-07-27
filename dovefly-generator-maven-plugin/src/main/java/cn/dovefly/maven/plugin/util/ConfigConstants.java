package cn.dovefly.maven.plugin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfigConstants {
	
	public final static String default_driver = "com.mysql.jdbc.Driver";
	
	public final static String default_url = "jdbc:mysql://139.196.235.181:3306/dovefly?jdbcCompliantTruncation=false";
	
	public final static String default_userName = "dovefly";
	
	public final static String default_password = "dovefly";
	
	public final static String default_dbProductName = "MySQL";
	
	public final static String default_baseProjectPath = "D:/IDE/workspace/deertt-admin";
	
	public final static String default_projectName = "deertt-admin";
	
	public final static String default_webAppName = "WebContent";
	
	public final static String default_toTablenameKeyword = "TableName";
	
	public final static String default_authorName = "fengcm";
	
	public final static String default_buildDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	
	public final static String default_javaPackageName = "com.deertt.module";
	
	public final static String default_javaFileRealPath = "src/com/deertt/module";
	
	public final static String default_jspSourcePath = "jsp/module";
	
	public final static String default_xlsTemplateBasePath = "files/templates/excel";
	
	public final static String[] default_no_build_fields = new String[]{"status", "sort", "create_by", "create_at", "modify_by", "modify_at"};
	
	public final static String[] default_keyColumn_keywords = new String[]{"title", "name"};

}