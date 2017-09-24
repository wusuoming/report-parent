<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
        package ${basepackage}.dao;
        import ${basepackage}.model.${className};
        import com.appc.framework.mybatis.dao.BasicCrudDao;
        import org.springframework.stereotype.Repository;

/**
 *  ${className}Dao ${table.remarks}
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: ${now?date}
 */
@Repository
public interface ${className}Dao  extends BasicCrudDao<${className}>  {

        }
