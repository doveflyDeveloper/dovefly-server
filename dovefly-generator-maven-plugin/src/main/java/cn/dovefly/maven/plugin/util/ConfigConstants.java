package cn.dovefly.maven.plugin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfigConstants {
	
	public final static String default_driver = "com.mysql.jdbc.Driver";
	
	public final static String default_url = "jdbc:mysql://139.196.235.181:3306/dovefly?jdbcCompliantTruncation=false";
	
	public final static String default_userName = "dovefly";
	
	public final static String default_password = "dovefly";
	
	public final static String default_buildDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	
	public final static String[] default_no_build_fields = new String[]{"status", "sort", "create_by", "create_at", "modify_by", "modify_at"};

}