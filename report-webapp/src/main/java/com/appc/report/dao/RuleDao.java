package com.appc.report.dao;

import com.appc.framework.mybatis.dao.BasicCrudDao;
import com.appc.report.model.Rule;
import org.springframework.stereotype.Repository;

/**
 * RuleDao
 *
 * @version : Ver 1.0
 * @author    : panda
 * @date    : 2017-9-15
 */
@Repository
public interface RuleDao extends BasicCrudDao<Rule> {

}
