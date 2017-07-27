package ${table.serviceImplPackageName};

import ${table.serviceFullClassName};
import ${table.repoFullClassName};
import ${table.entityFullClassName};
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * ${table.comment}
 * 
 * @author fengcm
 * @date 2017-07-27
 */
@Service
public class ${table.serviceImplClassName} implements ${table.serviceClassName} {

	private static Logger LOGGER = LoggerFactory.getLogger(${table.serviceImplClassName}.class);

	@Resource
	private ${table.repoClassName} repo;

	@Override
	@Transactional
	public int save(${table.entityClassName} entity) {
		if (entity == null) {
			return 0;
		}
		if (entity.get${table.keyColumn.propertyName?cap_first}() != null && entity.get${table.keyColumn.propertyName?cap_first}() > 0) {
			return repo.update(entity);
		} else {
			return repo.insert(entity);
		}
	}

	@Override
	@Transactional
	public int delete(${table.keyColumn.javaTypeShort} ${table.keyColumn.propertyName}) {
		return repo.deleteById(id);
	}

	@Override
	public ${table.entityClassName} get(${table.keyColumn.javaTypeShort} ${table.keyColumn.propertyName}) {
		return repo.selectByKey(key);
	}

	@Override
	public List<${table.entityClassName}> queryAllList() {
    	return repo.selectList();
    }

}

