package com.appc.report.dao;

import com.appc.framework.mybatis.dao.BasicCrudDao;
import com.appc.report.model.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * UserRoleDao
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-19
 */
@Repository
public interface UserRoleDao extends BasicCrudDao<UserRole> {

    void deleteByUserId(@Param("userId") Long userId);
}
