package com.appc.report.model;

import com.appc.framework.mybatis.annotation.Query;
import com.appc.framework.mybatis.annotation.Where;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * UserRole
 *
 * @version : Ver 1.0
 * @author    : panda
 * @date    : 2017-9-19
 */
@Entity
@Table
@Query
public class UserRole implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private Long userId;

    /**
     *
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private Integer roleId;

    /**
     *
     */
    @Id
    @Where
    @Getter(onMethod = @__({@GeneratedValue(), @ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private Integer id;

}
