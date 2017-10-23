package com.appc.report.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.appc.framework.mybatis.common.enums.DBType;
import com.appc.framework.mybatis.common.utils.ReflectionUtils;
import com.appc.framework.mybatis.executor.criteria.Criteria;
import com.appc.framework.mybatis.route.DBContextHolder;
import com.appc.framework.mybatis.route.DynamicDataSource;
import com.appc.report.common.db.PropertyHolder;
import com.appc.report.common.enums.ColumnType;
import com.appc.report.common.enums.DataSourseType;
import com.appc.report.dao.DataSourceDao;
import com.appc.report.dto.ColumnDto;
import com.appc.report.model.DataCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

@Service
public class DynamicDataSourceService {

    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Autowired
    private DataSourceDao dataSourceDao;

    public void putDataSource(Long sourceId) {
        try {
            Field field = DBContextHolder.class.getDeclaredField("targetDataSources");
            field.setAccessible(true);
            Map<String, DataSource> targetDataSources = (Map<String, DataSource>) field.get(DBContextHolder.class);
            Map<String, DataSource> targetDataSources2 = (Map<String, DataSource>) ReflectionUtils.getFieldValue(dynamicDataSource, "targetDataSources");
            Map<String, DataSource> resolvedDataSources = (Map<String, DataSource>) ReflectionUtils.getFieldValue(dynamicDataSource, "resolvedDataSources");


            if (!targetDataSources.containsKey("report_" + sourceId)) {
                com.appc.report.model.DataSource ds = dataSourceDao.getById(sourceId);
                DruidDataSource dataSource = new DruidDataSource();
                DataSourseType dbType = DataSourseType.getTypeByCode(ds.getSourceType());

                dataSource.setDriverClassName(dbType.getJdbcDriver());
                dataSource.setUsername(ds.getDataUsername());
                dataSource.setPassword(ds.getDataPassword());
                dataSource.setUrl(dbType.getJdbcUrlHelper().toJdbcUrl(ds));
                dataSource.setInitialSize(50);
                dataSource.setMinIdle(5);
                dataSource.setMaxActive(50);
                targetDataSources.put("report_" + sourceId, dataSource);
                targetDataSources2.put("report_" + sourceId, dataSource);
                resolvedDataSources.put("report_" + sourceId, dataSource);
            }
            DBContextHolder.putDataSource("report_" + sourceId);
        } catch (Exception ignored) {

        }


    }

    public List loadDBTable(Connection connection, String type) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        //获取数据库类型
        DBType dbType = DBType.getTypeByCode(connection.getMetaData().getDatabaseProductName());
        String catalog = connection.getCatalog();
        String schema = connection.getMetaData().getUserName();

        switch (dbType) {
            case MYSQL:
                break;
            case POSTGRESQL:
                schema = "%";
                break;
            case SQL_SERVER:
                schema = PropertyHolder.getGeneratorProperty("mssql.schema");
                break;
            case ORACLE:
                schema = schema.toUpperCase();
                break;
            default:

        }
        //获取数据库表
        ResultSet tables = metaData.getTables(catalog, schema, "%", new String[]{type.toUpperCase()});
        return resultSetToList(tables);


    }

    public List loadSqlColumn(Connection connection, String sql) throws SQLException {
        List list = new ArrayList();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(sql);
        ResultSetMetaData resultMetaData = results.getMetaData();
        int cols = resultMetaData.getColumnCount();
        for (int i = 1; i < cols; i++) {
            ColumnDto columnDto = new ColumnDto();
            columnDto.setColumnName(resultMetaData.getColumnName(i));
            columnDto.setTypeName(resultMetaData.getColumnTypeName(i));
            columnDto.setDecimalDigits(resultMetaData.getPrecision(i));
            columnDto.setColumnSize(resultMetaData.getScale(i));
            list.add(columnDto);
        }
        return list;
    }

    public List loadDBColumn(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        //获取数据库类型
        DBType dbType = DBType.getTypeByCode(connection.getMetaData().getDatabaseProductName());
        String schema = connection.getMetaData().getUserName();
        switch (dbType) {
            case MYSQL:
                return mysqlColumn(metaData.getColumns(connection.getCatalog(), schema, tableName, "%"));
            case POSTGRESQL:
                schema = "%";
                return resultSetToList(metaData.getColumns(connection.getCatalog(), schema, tableName, "%"));
            case SQL_SERVER:
                schema = PropertyHolder.getGeneratorProperty("mssql.schema");
                return resultSetToList(metaData.getColumns(connection.getCatalog(), schema, tableName, "%"));
            case ORACLE:
                schema = schema.toUpperCase();
                return resultSetToList(metaData.getColumns(connection.getCatalog(), schema, tableName, "%"));
            default:
                return resultSetToList(metaData.getColumns(connection.getCatalog(), schema, tableName, "%"));
        }
    }

    private List<ColumnDto> mysqlColumn(ResultSet rs) throws SQLException {
        List<Map<String, Object>> list = resultSetToList(rs);
        List<ColumnDto> columns = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : list) {
            ColumnDto columnDto = new ColumnDto();
            columnDto.setIsNullable("YES".equals(stringObjectMap.get("IS_NULLABLE")));
            columnDto.setColumnSize((Integer) stringObjectMap.get("COLUMN_SIZE"));
            columnDto.setDecimalDigits((Integer) stringObjectMap.get("DECIMAL_DIGITS"));
            columnDto.setTypeName((String) stringObjectMap.get("TYPE_NAME"));
            columnDto.setDataType(ColumnType.getTypeByCode((Integer) stringObjectMap.get("DATA_TYPE")).getDataType());
            columnDto.setColumnName((String) stringObjectMap.get("COLUMN_NAME"));
            columnDto.setRemarks((String) stringObjectMap.get("REMARKS"));
            columnDto.setTableName((String) stringObjectMap.get("TABLE_NAME"));
            columnDto.setTableSchem((String) stringObjectMap.get("TABLE_CAT"));
            columnDto.setIsAutoincrement("YES".equals(stringObjectMap.get("IS_AUTOINCREMENT")));
            columns.add(columnDto);
        }
        return columns;
    }

    private List<Map<String, Object>> resultSetToList(ResultSet rs) throws java.sql.SQLException {
        if (rs == null)
            return Collections.emptyList();
        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等
        int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数
        List<Map<String, Object>> list = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> rowData = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
        }
        return list;
    }

    public Page getCollectionData(DataCollection dataCollection, Criteria criteria, Pageable pageable) {
        return dataSourceDao.getCollectionData(dataCollection, criteria, pageable);
    }


}
