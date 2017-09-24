<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.controller;
import ${basepackage}.service.${className}Service;
import ${basepackage}.model.${className};
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  ${className}Controller
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: ${now?date}
 */
@RestController
@Api(value = "${className}Controller", description = "${table.remarks}相关")
@RequestMapping(value = "/${className?uncap_first}")
public class ${className}Controller  extends CommonController<${className}, ${className}Service<${className}>>{
	

}
