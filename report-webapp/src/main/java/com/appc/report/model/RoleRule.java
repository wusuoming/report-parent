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
    private Long id;

    /**
     *
     */

    @Where
    private Long roleId;

    /**
     *
     */

    @Where
    private Long ruleId;

    private Rule rule;

    @GeneratedValue()
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
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
