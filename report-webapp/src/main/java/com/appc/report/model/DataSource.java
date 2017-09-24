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
import java.util.Date;

/**
 * DataSource
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-23
 */
@Entity
@Table
@Data
@NoArgsConstructor
@Query
public class DataSource implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 数据源ID
     */
    @Id
    @Where
    @Getter(onMethod = @__({@GeneratedValue(), @ApiModelProperty("数据源ID")}))
    @Setter(onMethod = @__({@ApiModelProperty("数据源ID")}))
    private Long sourceId;

    /**
     *
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private String sourceName;

    /**
     *
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private String sourceType;

    /**
     *
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private String sourceIp;

    /**
     *
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private String sourcePort;

    /**
     *
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private String dataName;

    /**
     *
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private String dataUsername;

    /**
     *
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private String dataPassword;

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private Date createTime;

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private String characterEncoding;



}
