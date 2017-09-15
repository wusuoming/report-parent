package com.appc.report.model;

import com.appc.framework.mybatis.annotation.Query;
import com.appc.framework.mybatis.annotation.Where;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *  Rule
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-9-15
 */
@Entity
@Table
@Data
@NoArgsConstructor
@Query
public class Rule  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 权限ID     
     */
 	@Id
	@Where
	@Getter(onMethod = @__({@GeneratedValue(), @ApiModelProperty("权限ID")}))
	@Setter(onMethod = @__({@ApiModelProperty("权限ID")}))
	private Long ruleId;

	/**
     * 权限名称
     */

	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("权限名称")}))
	@Setter(onMethod = @__({@ApiModelProperty("权限名称")}))
	private String ruleName;

	/**
     * 创建时间
     */

	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("创建时间")}))
	@Setter(onMethod = @__({@ApiModelProperty("创建时间")}))
	private java.util.Date createTime;

	/**
     * 权限类型
     */

	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("权限类型")}))
	@Setter(onMethod = @__({@ApiModelProperty("权限类型")}))
	private String ruleType;

	/**
     * 权限表达式
     */

	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("权限表达式")}))
	@Setter(onMethod = @__({@ApiModelProperty("权限表达式")}))
	private String ruleExpression;

	/**
     *
     */

	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private Long ruleCate;
	
 }
