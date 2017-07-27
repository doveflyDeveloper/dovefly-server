package ${table.repoPackageName};

import com.xiaoka.freework.container.dao.CommonDao;
import com.xiaoka.freework.data.datasource.RoutingDataSourceDecision;
import ${table.entityFullClassName};
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
* ${table.comment}
*
* @author fengcm
* @date 2017-07-27
*/
@Repository
public class ${table.repoClassName} {

    @Resource
    private CommonDao ndsmDao;

    /**
    * 插入记录(包含所有字段)
    */
    public int insert(${table.entityClassName} entity) {
        return ndsmDao.mapper(${table.entityClassName}.class).source(RoutingDataSourceDecision.Source.MASTER)
            .sql("insert").session().insert(entity);
    }

    /**
    * 插入记录(不包含NULL字段)
    */
    public int insertSelective(${table.entityClassName} entity) {
        return ndsmDao.mapper(${table.entityClassName}.class).source(RoutingDataSourceDecision.Source.MASTER)
            .sql("insertSelective").session().insert(entity);
    }

    /**
    * 更新记录(包含所有字段)
    */
    public int updateByPrimaryKey(${table.entityClassName} entity) {
        return ndsmDao.mapper(${table.entityClassName}.class).source(RoutingDataSourceDecision.Source.MASTER)
            .sql("updateByPrimaryKey").session().update(entity);
    }

    /**
    * 更新记录(不包含NULL字段)
    */
    public int updateByPrimaryKeySelective(${table.entityClassName} entity) {
        return ndsmDao.mapper(${table.entityClassName}.class).source(RoutingDataSourceDecision.Source.MASTER)
            .sql("updateByPrimaryKeySelective").session().update(entity);
    }

    /**
    * 删除记录
    */
    public int deleteByPrimaryKey(${table.keyColumn.javaTypeShort} ${table.keyColumn.propertyName}) {
        return ndsmDao.mapper(${table.entityClassName}.class).source(RoutingDataSourceDecision.Source.MASTER)
            .sql("deleteByPrimaryKey").session().delete(${table.keyColumn.propertyName});
    }

    /**
    * 查询记录
    */
    public ${table.entityClassName} selectByPrimaryKey(${table.keyColumn.javaTypeShort} ${table.keyColumn.propertyName}) {
        return ndsmDao.mapper(${table.entityClassName}.class).source(RoutingDataSourceDecision.Source.SLAVE)
            .sql("selectByPrimaryKey").session().selectOne(${table.keyColumn.propertyName});
    }

    /**
    * 查询记录列表
    */
    public List<${table.entityClassName}> selectList() {
        return ndsmDao.mapper(${table.entityClassName}.class).source(RoutingDataSourceDecision.Source.SLAVE)
            .sql("selectList").session().selectList();
    }
}
