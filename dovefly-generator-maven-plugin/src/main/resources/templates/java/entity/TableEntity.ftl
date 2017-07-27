package ${table.entityPackageName};

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * ${table.comment}
 * 
 * @author fengcm
 * @date 2017-07-27
 */
public class ${table.entityClassName} implements Serializable {
	private static final long serialVersionUID = 1L;
	//----------开始entity的属性 ----------
	<#list table.columns as column>
    /**
    * ${column.comment}
    */
	private ${column.javaTypeShort} ${column.propertyName};
	
	</#list>
	//----------结束entity的属性----------

	//----------开始setter和getter方法----------
	<#list table.columns as column>
	public ${column.javaTypeShort} get${column.propertyName?cap_first}() {
		return ${column.propertyName};
	}

	public void set${column.propertyName?cap_first}(${column.javaTypeShort} ${column.propertyName}) {
		this.${column.propertyName} = ${column.propertyName};
	}
	
	</#list> 
	//----------结束setter和getter方法----------
}