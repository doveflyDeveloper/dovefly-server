package cn.dovefly.maven.plugin.util;

import cn.dovefly.maven.plugin.vo.DbTable;
import cn.dovefly.maven.plugin.vo.DbColumn;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBHelper {

	public Connection conn = null;
	
	public String driver = ConfigConstants.default_driver;
	
	public String url = ConfigConstants.default_url;
	
	public String userName = ConfigConstants.default_userName;
	
	public String password = ConfigConstants.default_password;
	
	public DBHelper() {
		super();
	}

	public DBHelper(String driver, String url, String userName, String password) {
		super();
		this.driver = driver;
		this.url = url;
		this.userName = userName;
		this.password = password;
	}

	String catalog = null;
	String schemaPattern = null;
	String tableNamePattern = null;
	
	/**
	 * 功能: 连接数据库
	 * 
	 * @param driver
	 * @param url
	 * @param userName
	 * @param password
	 */
	public boolean connectDB(String driver, String url, String userName, String password) {
		try {
			if (conn == null || conn.isClosed()) {
				Class.forName(driver); //初始化数据库连接
				conn = DriverManager.getConnection(url, userName, password);
				return true;
			} else {
				return true;
			}
		} catch (UnsupportedClassVersionError e) {
			return false;
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 获取表的列信息
	 * 
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public DbTable getTable(String tableName) throws SQLException{
		if (!connectDB(driver, url, userName, password)) {
			return null;
		}
		//设置表名和中文名
		String comment = getTableComment4MySql(tableName);

		//查找主键列
		String key = "";
		ResultSet rsPk = conn.getMetaData().getPrimaryKeys(null, null, tableName);
		if(rsPk.next()) {
			key = toLowerCase(rsPk.getString("COLUMN_NAME"));
		}

		List<DbColumn> columns = getColumns(tableName);
		//设置表字段
		DbColumn keyColumn = columns.get(0);

		for (DbColumn column : columns) {
			if (key.equals(column.getColumnName())) {
				keyColumn = column;
				break;
			}
		}

		DbTable table = new DbTable(tableName, comment, keyColumn, columns);

		return table;
	}

	/**
	 * 获得表的备注(适用Mysql)
	 *
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public String getTableComment4MySql(String tableName) throws SQLException {
		String tableComment = "";
		if (!connectDB(driver, url, userName, password)) {
			return "";
		}
		ResultSet rs = conn.prepareStatement("SELECT * FROM information_schema.TABLES WHERE table_name = '" + tableName + "'").executeQuery();
		if (rs.next()) {
			tableComment = rs.getString("TABLE_COMMENT");
		}
		return tableComment;
	}

	/**
	 * 获得表的字段信息
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public List<DbColumn> getColumns(String tableName) throws SQLException{
		List<DbColumn> list = new ArrayList<>();
		if (!connectDB(driver, url, userName, password)) {
			return list;
		}
		
		//设置每列属性
		ResultSet rs = conn.getMetaData().getColumns(catalog, schemaPattern, tableName, null);
		Map<String, DbColumn> map = new HashMap<>();
		while (rs.next()) {
			DbColumn column = new DbColumn();
//
//			System.out.println("TABLE_CAT=" + rs.getObject("TABLE_CAT"));
//			System.out.println("TABLE_SCHEM=" + rs.getObject("TABLE_SCHEM"));
//			System.out.println("TABLE_NAME=" + rs.getObject("TABLE_NAME"));
//			System.out.println("COLUMN_NAME=" + rs.getObject("COLUMN_NAME"));
//			System.out.println("DATA_TYPE=" + rs.getObject("DATA_TYPE"));
//			System.out.println("TYPE_NAME=" + rs.getObject("TYPE_NAME"));
//			System.out.println("COLUMN_SIZE=" + rs.getObject("COLUMN_SIZE"));
//			System.out.println("BUFFER_LENGTH=" + rs.getObject("BUFFER_LENGTH"));
//			System.out.println("DECIMAL_DIGITS=" + rs.getObject("DECIMAL_DIGITS"));
//			System.out.println("NUM_PREC_RADIX=" + rs.getObject("NUM_PREC_RADIX"));
//			System.out.println("NULLABLE=" + rs.getObject("NULLABLE"));
//			System.out.println("REMARKS=" + rs.getObject("REMARKS"));
//			System.out.println("COLUMN_DEF=" + rs.getObject("COLUMN_DEF"));
//			System.out.println("SQL_DATA_TYPE=" + rs.getObject("SQL_DATA_TYPE"));
//			System.out.println("SQL_DATETIME_SUB=" + rs.getObject("SQL_DATETIME_SUB"));
//			System.out.println("CHAR_OCTET_LENGTH=" + rs.getObject("CHAR_OCTET_LENGTH"));
//			System.out.println("ORDINAL_POSITION=" + rs.getObject("ORDINAL_POSITION"));
//			System.out.println("IS_NULLABLE=" + rs.getObject("IS_NULLABLE"));
//			System.out.println("SCOPE_CATALOG=" + rs.getObject("SCOPE_CATALOG"));
//			System.out.println("SCOPE_SCHEMA=" + rs.getObject("SCOPE_SCHEMA"));
//			System.out.println("SCOPE_TABLE=" + rs.getObject("SCOPE_TABLE"));
//			System.out.println("SOURCE_DATA_TYPE=" + rs.getObject("SOURCE_DATA_TYPE"));
//			System.out.println("IS_AUTOINCREMENT=" + rs.getObject("IS_AUTOINCREMENT"));
//			System.out.println("IS_GENERATEDCOLUMN=" + rs.getObject("IS_GENERATEDCOLUMN"));
//			System.out.println("=======================================================");

//			System.out.println(rs.getMetaData());
			column.setColumnName(rs.getString("COLUMN_NAME").toLowerCase());//列名称
			column.setComment(rs.getString("REMARKS"));//备注
//		  	column.setJdbcType(rs.getInt("DATA_TYPE"));//来自 java.sql.Types 的 SQL 类型
		  	column.setJdbcType(rs.getString("TYPE_NAME").toUpperCase());//数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的
//		  	column.setTypeName(toLowerCase(rs.getString("TYPE_NAME")));//
			column.setLength(rs.getInt("COLUMN_SIZE"));//COLUMN_SIZE 列表示给定列的指定列大小。对于数值数据，这是最大精度。对于字符数据，这是字符长度。对于日期时间数据类型，这是 String 表示形式的字符长度（假定允许的最大小数秒组件的精度）。对于二进制数据，这是字节长度。对于 ROWID 数据类型，这是字节长度。对于列大小不适用的数据类型，则返回 Null。
//			column.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));//小数部分的位数。对于 DECIMAL_DIGITS 不适用的数据类型，则返回 Null。
			column.setNullable(rs.getInt("NULLABLE") == 1);//是否允许使用 NULL。
		  	column.setComment(toLowerCase(rs.getString("REMARKS")));//列注释
			column.setDefaultValue(toLowerCase(rs.getString("COLUMN_DEF")));//该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null）
			
			list.add(column);
			map.put(column.getColumnName(), column);
		}
		
		//设置字段数据类型
		ResultSetMetaData rsmd = conn.prepareStatement("SELECT * FROM " + tableName).executeQuery().getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			String columnName = rsmd.getColumnName(i);//获取指定列的名称。
			String columnClassName = rsmd.getColumnClassName(i);//如果调用方法 ResultSet.getObject 从列中获取值，则返回构造其实例的 Java 类的完全限定名称。
			if(map.get(columnName) != null) {
				map.get(columnName).setJavaType(columnClassName);
			}
		}
		return list;
	}
	
	/**
	 * 小写形式
	 * @param str
	 * @return
	 */
	public String toLowerCase(String str){
		return str == null ? "" : str.toLowerCase();
	}
	
	public static void main(String[] args){
		DBHelper db = new DBHelper();
		try {
			//System.out.println(db.getTableNames());
			System.out.println(db.getTable("test_employee"));
			//System.out.println(db.getColumns("t_notice"));
			//System.out.println(db.getTableComment4MySql("t_notice"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
