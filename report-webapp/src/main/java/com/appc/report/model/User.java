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
 *  User
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
public class User  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 用户ID     
     */
 	@Id
	@Where
	@Getter(onMethod = @__({@GeneratedValue(), @ApiModelProperty("用户ID")}))
	@Setter(onMethod = @__({@ApiModelProperty("用户ID")}))
	private java.lang.String openId;
	
	/**
     * 昵称     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("昵称")}))
	@Setter(onMethod = @__({@ApiModelProperty("昵称")}))
	private java.lang.String nickName;
	
	/**
     * 性别     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("性别")}))
	@Setter(onMethod = @__({@ApiModelProperty("性别")}))
	private java.lang.String sex;
	
	/**
     * 城市     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("城市")}))
	@Setter(onMethod = @__({@ApiModelProperty("城市")}))
	private java.lang.String city;
	
	/**
     * 省份     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("省份")}))
	@Setter(onMethod = @__({@ApiModelProperty("省份")}))
	private java.lang.String province;
	
	/**
     * 国家     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("国家")}))
	@Setter(onMethod = @__({@ApiModelProperty("国家")}))
	private java.lang.String country;
	
	/**
     * 头像地址     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("头像地址")}))
	@Setter(onMethod = @__({@ApiModelProperty("头像地址")}))
	private java.lang.String headImgUrl;
	
	/**
     * 语言     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("语言")}))
	@Setter(onMethod = @__({@ApiModelProperty("语言")}))
	private java.lang.String language;
	
	/**
     * 用户状态     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("用户状态")}))
	@Setter(onMethod = @__({@ApiModelProperty("用户状态")}))
	private java.lang.String statusCode;
	
	/**
     * 邮箱     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("邮箱")}))
	@Setter(onMethod = @__({@ApiModelProperty("邮箱")}))
	private java.lang.String email;
	
	/**
     * 电话号码     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("电话号码")}))
	@Setter(onMethod = @__({@ApiModelProperty("电话号码")}))
	private java.lang.Long phone;
	
	/**
     * 电话号码国家代码     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("电话号码国家代码")}))
	@Setter(onMethod = @__({@ApiModelProperty("电话号码国家代码")}))
	private java.lang.Integer countryCode;
	
	/**
     * 创建时间     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("创建时间")}))
	@Setter(onMethod = @__({@ApiModelProperty("创建时间")}))
	private java.util.Date createTime;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.util.Date subscribeTime;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String idCard;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String wxOpenId;
	
 }
