package ${table.servicePackageName};

import ${table.entityFullClassName};
import java.util.List;

/**
* ${table.comment}
*
* @author fengcm
* @date 2017-07-27
*/
public interface ${table.serviceClassName} {

    int save(${table.entityClassName} entity);

    int delete(${table.keyColumn.javaTypeShort} ${table.keyColumn.propertyName});

    ${table.entityClassName} get(${table.keyColumn.javaTypeShort} ${table.keyColumn.propertyName});

    List<${table.entityClassName}> queryAllList();
}
