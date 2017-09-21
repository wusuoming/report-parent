package com.appc.report.service.impl;

import com.appc.report.dao.RuleDao;
import com.appc.report.model.Rule;
import com.appc.report.service.RuleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RuleServiceImpl
 *
 * @version : Ver 1.0
 * @author    : panda
 * @date    : 2017-9-15
 */
@Service
public class RuleServiceImpl extends CommonServiceImpl<Rule, RuleDao> implements RuleService {


    @Override
    public List<Rule> getMenuList(Long userId) {
        return baseDao.getMenuList(userId);
    }
}
