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
 * AdminUser
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
public class AdminUser implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     */
    @Id
    @Where
    @Getter(onMethod = @__({@GeneratedValue(), @ApiModelProperty("账户ID")}))
    @Setter(onMethod = @__({@ApiModelProperty("账户ID")}))
    private java.lang.Long id;

    /**
     * 用户名
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("用户名")}))
    @Setter(onMethod = @__({@ApiModelProperty("用户名")}))
    private java.lang.String username;

    /**
     * 密码
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("密码")}))
    @Setter(onMethod = @__({@ApiModelProperty("密码")}))
    private java.lang.String password;

    /**
     * 昵称
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("昵称")}))
    @Setter(onMethod = @__({@ApiModelProperty("昵称")}))
    private java.lang.String nikeName;

    /**
     * 创建时间
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("创建时间")}))
    @Setter(onMethod = @__({@ApiModelProperty("创建时间")}))
    private java.util.Date createTime;

    /**
     *
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private java.lang.String areaId;

}
