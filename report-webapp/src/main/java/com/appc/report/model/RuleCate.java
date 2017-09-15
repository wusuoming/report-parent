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
 *  RuleCate
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
public class RuleCate  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 分类ID     
     */
 	@Id
	@Where
	@Getter(onMethod = @__({@GeneratedValue(), @ApiModelProperty("分类ID")}))
	@Setter(onMethod = @__({@ApiModelProperty("分类ID")}))
	private Long cateId;

	/**
     * 分类名称
     */

	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("分类名称")}))
	@Setter(onMethod = @__({@ApiModelProperty("分类名称")}))
	private String cateName;
	
	/**
     * 创建时间     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("创建时间")}))
	@Setter(onMethod = @__({@ApiModelProperty("创建时间")}))
	private java.util.Date createTime;
	
 }
