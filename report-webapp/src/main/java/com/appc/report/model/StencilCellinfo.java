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
 *  StencilCellinfo
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
public class StencilCellinfo  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String stencilId;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.Integer fieldLevel;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String levelCode;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.Integer isLeaf;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.Integer startRow;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.Integer nextRow;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.Integer startColumn;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.Integer nextColumn;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.Integer columnWidth;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String fieldEname;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String fieldFname;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String fieldCname;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.Integer dataType;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.util.Date lastVer;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.Integer isSort;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.Integer isQuery;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.Integer isHide;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String fieldCode;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String parentCode;
	
	/**
     * 是否汇总     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("是否汇总")}))
	@Setter(onMethod = @__({@ApiModelProperty("是否汇总")}))
	private java.lang.Integer isSum;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String link;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String filterValue;
	
 }
