package com.appc.report.service.impl;

import basic.common.core.utils.MD5Util;
import com.appc.report.dao.AdminUserDao;
import com.appc.report.dao.UserRoleDao;
import com.appc.report.model.AdminUser;
import com.appc.report.model.UserRole;
import com.appc.report.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;

/**
 * AdminUserServiceImpl
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-14
 */
@Service
public class AdminUserServiceImpl extends CommonServiceImpl<AdminUser, AdminUserDao> implements AdminUserService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void save(AdminUser user) {
        if (user.getId() != null) {
            userRoleDao.deleteByUserId(user.getId());
            updateById(user);
        } else {
            user.setCreateTime(new Date());
            user.setStatus("0");
            user.setPassword(MD5Util.getMD5String(user.getPassword()));
            insert(user);
        }
        if (!CollectionUtils.isEmpty(user.getUserRoles())) {
            for (UserRole userRole : user.getUserRoles()) {
                userRole.setUserId(user.getId());
            }
            userRoleDao.insertBatch(user.getUserRoles());

        }
    }
}
