package com.appc.report.dao;

import com.appc.framework.mybatis.dao.BasicCrudDao;
import com.appc.framework.mybatis.executor.criteria.Criteria;
import com.appc.report.model.DataCollection;
import com.appc.report.model.DataSource;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * DataSourceDao 数据源
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-9-23
 */
@Repository
public interface DataSourceDao extends BasicCrudDao<DataSource> {

    Page getCollectionData(@Param("dataCollection") DataCollection dataCollection, Criteria criteria, Pageable pageable);
}
