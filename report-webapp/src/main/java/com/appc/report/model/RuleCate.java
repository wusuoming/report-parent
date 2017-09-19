package com.appc.report.model;

import com.appc.framework.mybatis.annotation.Lazy;
import com.appc.framework.mybatis.annotation.Property;
import com.appc.framework.mybatis.annotation.Query;
import com.appc.framework.mybatis.annotation.Where;
import com.appc.framework.mybatis.common.enums.LazyType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * RuleCate
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-15
 */
@Entity
@Table
@Query
public class RuleCate implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @Id
    @Where
    private Long cateId;

    /**
     * 分类名称
     */

    @Where
    private String cateName;

    /**
     * 创建时间
     */

    @Where
    private java.util.Date createTime;

    private List<Rule> rules;

    @GeneratedValue()
    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(insertable = false, updatable = false)
    @Lazy(type = LazyType.CLASS, classZ = Rule.class, isList = true, propertys = @Property(propertyName = "ruleCate", propertyExpression = "#cateId"))
    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
}
