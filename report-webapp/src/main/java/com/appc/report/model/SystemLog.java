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
 * SystemLog
 *
 * @version : Ver 1.0
 * @author    : panda
 * @date    : 2017-9-14
 */
@Entity
@Table
@Data
@NoArgsConstructor
@Query
public class SystemLog implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @Id
    @Where
    @Getter(onMethod = @__({@GeneratedValue(), @ApiModelProperty("日志ID")}))
    @Setter(onMethod = @__({@ApiModelProperty("日志ID")}))
    private java.lang.Long logId;

    /**
     * 方法名
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("方法名")}))
    @Setter(onMethod = @__({@ApiModelProperty("方法名")}))
    private java.lang.String methodName;

    /**
     * 请求地址
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("请求地址")}))
    @Setter(onMethod = @__({@ApiModelProperty("请求地址")}))
    private java.lang.String requestIp;

    /**
     * 请求地址
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("请求地址")}))
    @Setter(onMethod = @__({@ApiModelProperty("请求地址")}))
    private java.lang.String requestUrl;

    /**
     * 请求时间
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("请求时间")}))
    @Setter(onMethod = @__({@ApiModelProperty("请求时间")}))
    private java.util.Date requestDate;

    /**
     * 请求参数
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("请求参数")}))
    @Setter(onMethod = @__({@ApiModelProperty("请求参数")}))
    private java.lang.String requestArguments;

    /**
     * 响应异常
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("响应异常")}))
    @Setter(onMethod = @__({@ApiModelProperty("响应异常")}))
    private java.lang.String responseException;

    /**
     * 响应结果
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("响应结果")}))
    @Setter(onMethod = @__({@ApiModelProperty("响应结果")}))
    private java.lang.String responseResult;

    /**
     * 响应时间
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("响应时间")}))
    @Setter(onMethod = @__({@ApiModelProperty("响应时间")}))
    private java.lang.Long responseTime;

    /**
     * 请求用户
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("请求用户")}))
    @Setter(onMethod = @__({@ApiModelProperty("请求用户")}))
    private java.lang.Long requestUser;

}
