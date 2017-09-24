package com.appc.report.common.helper.jdbc;

import com.appc.report.common.helper.JdbcUrlHelper;
import com.appc.report.model.DataSource;

public class PostgreSQLJdbcUrlHelper extends JdbcUrlHelper {
    @Override
    public String toJdbcUrl(DataSource ds) {
        return "jdbc:postgresql://<host>:<port>/<database_name>";
    }

    @Override
    public void loadUrl(String url, DataSource ds) {

    }
}
