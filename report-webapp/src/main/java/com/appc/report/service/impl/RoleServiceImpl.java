package com.appc.report.service.impl;

import basic.common.core.exception.BaseException;
import com.appc.framework.mybatis.executor.criteria.EntityCriteria;
import com.appc.report.dao.RoleDao;
import com.appc.report.dao.RoleRuleDao;
import com.appc.report.dao.UserRoleDao;
import com.appc.report.model.Role;
import com.appc.report.model.RoleRule;
import com.appc.report.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void save(Role role) {
        if (role.getRoleId() != null) {
            roleRuleDao.deleteByRoleId(role.getRoleId());
            updateById(role);
        } else {
            role.setCreateTime(new Date());
            role.setStatus("0");
            insert(role);
        }
        if (!CollectionUtils.isEmpty(role.getRoleRules())) {
            List<RoleRule> roleRules = new ArrayList<>();
            for (RoleRule roleRule : role.getRoleRules()) {
                roleRule.setRoleId(role.getRoleId());
                if (roleRule.getRuleId() != null) {
                    roleRules.add(roleRule);
                }
            }
            roleRuleDao.insertBatch(roleRules);

        }
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            if (userRoleDao.getEntityCount(EntityCriteria.build().eq("role_id", id)) > 0) {
                throw new BaseException("020003");
            }
            roleRuleDao.deleteByRoleId(id);
            deleteById(id);
        }
    }
}
