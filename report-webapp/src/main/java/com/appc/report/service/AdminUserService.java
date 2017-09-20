package com.appc.report.service;

import com.appc.report.model.AdminUser;

/**
 *  AdminUserService
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-9-14 
 */
public interface AdminUserService    extends CommonService<AdminUser>{

    void save(AdminUser user);
}
