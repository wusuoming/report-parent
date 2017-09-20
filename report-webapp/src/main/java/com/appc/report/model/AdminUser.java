package com.appc.report.model;

import com.appc.framework.mybatis.annotation.Lazy;
import com.appc.framework.mybatis.annotation.Property;
import com.appc.framework.mybatis.annotation.Query;
import com.appc.framework.mybatis.common.enums.LazyType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * AdminUser
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-14
 */
@Entity
@Table
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
    private java.lang.Long id;

    /**
     * 用户名
     */

    private java.lang.String username;

    /**
     * 密码
     */

    private java.lang.String password;

    /**
     * 昵称
     */

    private java.lang.String nikeName;

    /**
     * 创建时间
     */

    private java.util.Date createTime;

    /**
     *
     */


    private java.lang.String areaId;


    private String status;


    private java.lang.Long phone;


    private String email;


    private List<UserRole> userRoles;

    @GeneratedValue()
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(insertable = false, updatable = false)
    @Lazy(type = LazyType.CLASS, isList = true, classZ = UserRole.class, propertys = @Property(propertyName = "userId", propertyExpression = "#id"))
    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
