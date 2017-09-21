package com.appc.report.dao;

import com.appc.framework.mybatis.dao.BasicCrudDao;
import com.appc.report.model.Rule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RuleDao
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-15
 */
@Repository
public interface RuleDao extends BasicCrudDao<Rule> {

    List<Rule> getMenuList(@Param("userId") Long userId);
}
