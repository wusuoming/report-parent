package com.appc.report.service.impl;

import com.appc.report.dao.RoleDao;
import com.appc.report.dao.RoleRuleDao;
import com.appc.report.model.Role;
import com.appc.report.model.RoleRule;
import com.appc.report.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * RoleServiceImpl
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-19
 */
@Service
public class RoleServiceImpl extends CommonServiceImpl<Role, RoleDao> implements RoleService {

    @Autowired
    private RoleRuleDao roleRuleDao;

    @Override
    public void save(Role role) {
        if (role.getRoleId() != null) {
            roleRuleDao.deleteByRoleId(role.getRoleId());
            updateById(role);
        } else {
            role.setCreateTime(new Date());
            insert(role);
        }
        for (RoleRule roleRule : role.getRoleRules()) {
            roleRule.setRoleId(role.getRoleId());
        }
        roleRuleDao.insertBatch(role.getRoleRules());
    }
}
