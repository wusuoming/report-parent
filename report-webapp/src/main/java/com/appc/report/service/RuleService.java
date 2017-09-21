package com.appc.report.service;

import com.appc.report.model.Rule;

import java.util.List;

/**
 * RuleService
 *
 * @version : Ver 1.0
 * @author    : panda
 * @date    : 2017-9-15
 */
public interface RuleService extends CommonService<Rule> {
    List<Rule> getMenuList(Long userId);
}
