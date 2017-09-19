package com.appc.report.service;

import com.appc.report.model.Role;

/**
 * RoleService
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-19
 */
public interface RoleService extends CommonService<Role> {

    void save(Role role);
}
