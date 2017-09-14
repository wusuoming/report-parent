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
 *  CommonRegion
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
public class CommonRegion  implements Serializable {
	
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
	private java.lang.String commonRegionId;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String regionName;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String parentRegionId;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String regionDescription;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String regionPath;
	
 }
