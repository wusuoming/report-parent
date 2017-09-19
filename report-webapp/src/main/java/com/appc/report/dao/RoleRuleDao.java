package com.appc.report.dao;

import com.appc.framework.mybatis.dao.BasicCrudDao;
import com.appc.report.model.RoleRule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * RoleRuleDao
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-19
 */
@Repository
public interface RoleRuleDao extends BasicCrudDao<RoleRule> {

    void deleteByRoleId(@Param("roleId") Integer roleId);
}
