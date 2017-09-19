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
 * Role
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-19
 */
@Entity
@Table
@Query
public class Role implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @Id
    @Where
    private Integer roleId;

    /**
     * 角色名称
     */

    @Where
    private String roleName;

    /**
     *
     */

    @Where

    private String roleDiscription;

    /**
     *
     */

    @Where

    private String status;

    /**
     * 创建时间
     */

    @Where
    private java.util.Date createTime;

    private List<RoleRule> roleRules;

    @GeneratedValue()
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDiscription() {
        return roleDiscription;
    }

    public void setRoleDiscription(String roleDiscription) {
        this.roleDiscription = roleDiscription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(insertable = false, updatable = false)
    @Lazy(type = LazyType.CLASS, isList = true, classZ = RoleRule.class, propertys = @Property(propertyName = "roleId", propertyExpression = "#roleId"))
    public List<RoleRule> getRoleRules() {
        return roleRules;
    }

    public void setRoleRules(List<RoleRule> roleRules) {
        this.roleRules = roleRules;
    }
}
