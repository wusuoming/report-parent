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
 *  StencilMaster
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
public class StencilMaster  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     *      
     */
 	@Id
	@Where
	@Getter(onMethod = @__({@GeneratedValue(), @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.Long stencilId;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String stencilCname;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String stencilEname;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String stencilRemark;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String createCommonRegionId;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String commonRegionId;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.Integer stencilStatus;
	
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
	private java.lang.String detailSql;
	
	/**
     * 是否分页     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("是否分页")}))
	@Setter(onMethod = @__({@ApiModelProperty("是否分页")}))
	private java.lang.Integer isPaging;
	
	/**
     * 是否导出     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("是否导出")}))
	@Setter(onMethod = @__({@ApiModelProperty("是否导出")}))
	private java.lang.Integer isExport;
	
 }
