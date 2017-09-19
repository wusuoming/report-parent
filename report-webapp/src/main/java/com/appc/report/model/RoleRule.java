package com.appc.report.model;

import com.appc.framework.mybatis.annotation.Lazy;
import com.appc.framework.mybatis.annotation.Property;
import com.appc.framework.mybatis.annotation.Query;
import com.appc.framework.mybatis.annotation.Where;
import com.appc.framework.mybatis.common.enums.LazyType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * RoleRule
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-19
 */
@Entity
@Table
@Query
public class RoleRule implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @Id
    @Where
    private Integer id;

    /**
     *
     */

    @Where
    private Integer roleId;

    /**
     *
     */

    @Where
    private Integer ruleId;

    private Rule rule;

    @GeneratedValue()
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    @Column(insertable = false, updatable = false)
    @Lazy(type = LazyType.CLASS, classZ = Rule.class, propertys = @Property(propertyName = "ruleId", propertyExpression = "#ruleId"))
    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
