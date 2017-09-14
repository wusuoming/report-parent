package com.appc.report.model;

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
 *  Menu
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-9-14
 */
@Entity
@Table
@Data
@NoArgsConstructor
@Query
public class Menu  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 菜单ID     
     */
 	@Id
	@Where
	@Getter(onMethod = @__({@GeneratedValue(), @ApiModelProperty("菜单ID")}))
	@Setter(onMethod = @__({@ApiModelProperty("菜单ID")}))
	private java.lang.String menuId;
	
	/**
     * 菜单名称     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("菜单名称")}))
	@Setter(onMethod = @__({@ApiModelProperty("菜单名称")}))
	private java.lang.String menuName;
	
	/**
     * 上级菜单ID     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("上级菜单ID")}))
	@Setter(onMethod = @__({@ApiModelProperty("上级菜单ID")}))
	private java.lang.String parentId;
	
	/**
     * 状态编码     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("状态编码")}))
	@Setter(onMethod = @__({@ApiModelProperty("状态编码")}))
	private java.lang.String statusCode;
	
	/**
     * 链接地址     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("链接地址")}))
	@Setter(onMethod = @__({@ApiModelProperty("链接地址")}))
	private java.lang.String url;
	
	/**
     * 图标     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("图标")}))
	@Setter(onMethod = @__({@ApiModelProperty("图标")}))
	private java.lang.String icon;
	
	/**
     * 描述     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("描述")}))
	@Setter(onMethod = @__({@ApiModelProperty("描述")}))
	private java.lang.String remark;
	
	/**
     * 创建时间     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("创建时间")}))
	@Setter(onMethod = @__({@ApiModelProperty("创建时间")}))
	private java.util.Date createTime;
	
 }
