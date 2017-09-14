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
 *  Contacts
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
public class Contacts  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID     
     */
 	@Id
	@Where
	@Getter(onMethod = @__({@GeneratedValue(), @ApiModelProperty("主键ID")}))
	@Setter(onMethod = @__({@ApiModelProperty("主键ID")}))
	private java.lang.Long id;
	
	/**
     * 姓名     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("姓名")}))
	@Setter(onMethod = @__({@ApiModelProperty("姓名")}))
	private java.lang.String name;
	
	/**
     * 身份证号码     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("身份证号码")}))
	@Setter(onMethod = @__({@ApiModelProperty("身份证号码")}))
	private java.lang.String idNo;
	
	/**
     * 手机号码     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("手机号码")}))
	@Setter(onMethod = @__({@ApiModelProperty("手机号码")}))
	private java.lang.String phone;
	
	/**
     * 用户ID     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("用户ID")}))
	@Setter(onMethod = @__({@ApiModelProperty("用户ID")}))
	private java.lang.String userId;
	
	/**
     * 用户身份证号码     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("用户身份证号码")}))
	@Setter(onMethod = @__({@ApiModelProperty("用户身份证号码")}))
	private java.lang.String userIdNo;
	
 }
