package com.appc.report.common.enums;

import com.alibaba.druid.util.JdbcConstants;
import com.appc.report.common.helper.JdbcUrlHelper;
import com.appc.report.common.helper.jdbc.MysqlJdbcUrlHelper;
import com.appc.report.common.helper.jdbc.OracleJdbcUrlHelper;
import com.appc.report.common.helper.jdbc.PostgreSQLJdbcUrlHelper;
import com.appc.report.common.helper.jdbc.SQLServerJdbcUrlHelper;

public enum DataSourseType {
    /**
     * MySQL数据库
     */
    MYSQL("MySQL", new MysqlJdbcUrlHelper(), JdbcConstants.MYSQL_DRIVER),
    /**
     * PostgreSQL数据库
     */
    POSTGRESQL("PostgreSQL", new PostgreSQLJdbcUrlHelper(), JdbcConstants.POSTGRESQL_DRIVER),
    /**
     * SQL Server数据库
     */
    SQL_SERVER("Microsoft SQL Server", new SQLServerJdbcUrlHelper(), JdbcConstants.SQL_SERVER_DRIVER),
    /**
     * Oracler数据库
     */
    ORACLE("Oracle", new OracleJdbcUrlHelper(), JdbcConstants.ORACLE_DRIVER);

    private final String jdbcDriver;
    private String code;

    private JdbcUrlHelper jdbcUrlHelper;

    DataSourseType(String code, JdbcUrlHelper jdbcUrlHelper, String jdbcDriver) {
        this.code = code;
        this.jdbcUrlHelper = jdbcUrlHelper;
        this.jdbcDriver = jdbcDriver;

    }


    public static DataSourseType getTypeByCode(String code) {
        for (DataSourseType element : DataSourseType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public JdbcUrlHelper getJdbcUrlHelper() {
        return jdbcUrlHelper;
    }

    public void setJdbcUrlHelper(JdbcUrlHelper jdbcUrlHelper) {
        this.jdbcUrlHelper = jdbcUrlHelper;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }
}
