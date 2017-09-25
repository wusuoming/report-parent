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
 * DataCollection
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-25
 */
@Entity
@Table
@Data
@NoArgsConstructor
@Query
public class DataCollection implements Serializable {

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
    private Long collectionId;

    /**
     *
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private Long parentId;

    /**
     *
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private String collectionName;

    /**
     *
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private String collectionType;

    /**
     *
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private String collectionValue;

    /**
     *
     */

    @Where
    @Getter(onMethod = @__({@ApiModelProperty("")}))
    @Setter(onMethod = @__({@ApiModelProperty("")}))
    private String collectionDiscription;

    private Date createTime;
    private String path;

    private Long sourceId;

}
