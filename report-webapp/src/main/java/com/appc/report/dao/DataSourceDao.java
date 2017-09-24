        package com.appc.report.dao;
        import com.appc.report.model.DataSource;
        import com.appc.framework.mybatis.dao.BasicCrudDao;
        import org.springframework.stereotype.Repository;

/**
 *  DataSourceDao 数据源
 *
 * @version : Ver 1.0
 * @author	: panda
 * @date	: 2017-9-23
 */
@Repository
public interface DataSourceDao  extends BasicCrudDao<DataSource>  {

        }
