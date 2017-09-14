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
 *  PayOrder
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
public class PayOrder  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 订单编号     
     */
 	@Id
	@Where
	@Getter(onMethod = @__({@GeneratedValue(), @ApiModelProperty("订单编号")}))
	@Setter(onMethod = @__({@ApiModelProperty("订单编号")}))
	private java.lang.String orderNo;
	
	/**
     * 订单名称     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("订单名称")}))
	@Setter(onMethod = @__({@ApiModelProperty("订单名称")}))
	private java.lang.String orderName;
	
	/**
     * 用户ID     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("用户ID")}))
	@Setter(onMethod = @__({@ApiModelProperty("用户ID")}))
	private java.lang.String userId;
	
	/**
     * 支付类型     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("支付类型")}))
	@Setter(onMethod = @__({@ApiModelProperty("支付类型")}))
	private java.lang.String payType;
	
	/**
     * 支付时间     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("支付时间")}))
	@Setter(onMethod = @__({@ApiModelProperty("支付时间")}))
	private java.util.Date payTime;
	
	/**
     * 支付金额     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("支付金额")}))
	@Setter(onMethod = @__({@ApiModelProperty("支付金额")}))
	private java.lang.Long payMoney;
	
	/**
     * 支付状态     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("支付状态")}))
	@Setter(onMethod = @__({@ApiModelProperty("支付状态")}))
	private java.lang.String payStatus;
	
	/**
     * 充值时间     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("充值时间")}))
	@Setter(onMethod = @__({@ApiModelProperty("充值时间")}))
	private java.util.Date rechargeTime;
	
	/**
     * 充值状态     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("充值状态")}))
	@Setter(onMethod = @__({@ApiModelProperty("充值状态")}))
	private java.lang.String rechargeStatus;
	
	/**
     * 充值卡号     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("充值卡号")}))
	@Setter(onMethod = @__({@ApiModelProperty("充值卡号")}))
	private java.lang.String cardNo;
	
	/**
     * 充值卡序列号     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("充值卡序列号")}))
	@Setter(onMethod = @__({@ApiModelProperty("充值卡序列号")}))
	private java.lang.String seqNo;
	
	/**
     * 卡类型     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("卡类型")}))
	@Setter(onMethod = @__({@ApiModelProperty("卡类型")}))
	private java.lang.String cardType;
	
	/**
     * 充值人身份证号码     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("充值人身份证号码")}))
	@Setter(onMethod = @__({@ApiModelProperty("充值人身份证号码")}))
	private java.lang.String idCard;
	
	/**
     * 充值人姓名     
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("充值人姓名")}))
	@Setter(onMethod = @__({@ApiModelProperty("充值人姓名")}))
	private java.lang.String userName;
	
	/**
     *      
     */
 	
	@Where
	@Getter(onMethod = @__({ @ApiModelProperty("")}))
	@Setter(onMethod = @__({@ApiModelProperty("")}))
	private java.lang.String transactionId;
	
 }
