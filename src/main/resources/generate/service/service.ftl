package ${packageName};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.data.jpa.service.BaseService;
import ${autowiredPackege}.${entityName}Dao;
import ${entityPackageName};

@Service
public class ${entityName}Service extends BaseService<${entityName}, ${idType}> {

	@Autowired
	private ${entityName}Dao ${entitySimpleName}Dao;

	@Override
	protected IBaseDao<${entityName}, ${idType}> getEntityDao() {
		return ${entitySimpleName}Dao;
	}

}
