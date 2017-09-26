package com.appc.report.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.appc.framework.mybatis.route.DBContextHolder;
import com.appc.framework.mybatis.route.DynamicDataSource;
import com.appc.report.common.enums.DataSourseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.Map;

@Service
public class DynamicDataSourceService {

    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Autowired
    private DataSourceService dataSourceService;

    public void putDataSource(Long sourceId) {
        try {
            Field field = DBContextHolder.class.getDeclaredField("targetDataSources");
            field.setAccessible(true);
            Map<String, DataSource> resolvedDataSources = (Map<String, DataSource>) field.get(DBContextHolder.class);
            if (!resolvedDataSources.containsKey("report_" + sourceId)) {
                com.appc.report.model.DataSource ds = dataSourceService.getById(sourceId);
                DruidDataSource dataSource = new DruidDataSource();
                DataSourseType dbType = DataSourseType.getTypeByCode(ds.getSourceType());

                dataSource.setDriverClassName(dbType.getJdbcDriver());
                dataSource.setUsername(ds.getDataUsername());
                dataSource.setPassword(ds.getDataPassword());
                dataSource.setUrl(dbType.getJdbcUrlHelper().toJdbcUrl(ds));
                dataSource.setInitialSize(5);
                dataSource.setMinIdle(1);
                dataSource.setMaxActive(10);
                resolvedDataSources.put("report_" + sourceId, dataSource);
            }
            DBContextHolder.putDataSource("report_" + sourceId);
        } catch (Exception ignored) {

        }


    }

}
