<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service.impl;
import ${basepackage}.service.${className}Service;
import ${basepackage}.dao.${className}Dao;
import ${basepackage}.model.${className};
import org.springframework.stereotype.Service;

/**
 *  ${className}ServiceImpl
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: ${now?date}
 */
@Service
public class ${className}ServiceImpl  extends CommonServiceImpl<${className},${className}Dao>  implements ${className}Service {


}
