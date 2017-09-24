<#include "/macro.include"/> 
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.model;

import com.appc.framework.mybatis.annotation.Query;
import com.appc.framework.mybatis.annotation.Where;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 *  ${className}
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: ${now?date}
 */
@Entity
@Table
@Data
@NoArgsConstructor
@Query
public class ${className}  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	<#list table.columns as column>
	/**
     * ${column.remarks}     
     */
 	<#if column.pk >@Id</#if>
	@Where
	@Getter(onMethod = @__({<#if column.pk>@GeneratedValue(),</#if> @ApiModelProperty("${column.remarks}")}))
	@Setter(onMethod = @__({@ApiModelProperty("${column.remarks}")}))
	private ${column.javaType} ${column.columnNameLower};
	
	</#list>
 }
