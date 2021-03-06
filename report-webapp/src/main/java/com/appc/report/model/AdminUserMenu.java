package com.appc.report.model;

import com.appc.framework.mybatis.annotation.Query;
import com.appc.framework.mybatis.annotation.Where;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * AdminUserMenu
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-14
 */
@Entity
@Table
@Data
@NoArgsConstructor
@Query
public class AdminUserMenu implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("用户ID")}))
    @Setter(onMethod = @__({@ApiModelProperty("用户ID")}))
    private java.lang.String userId;

    /**
     * 菜单ID
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("菜单ID")}))
    @Setter(onMethod = @__({@ApiModelProperty("菜单ID")}))
    private java.lang.String menuId;

}
