package com.appc.report.common.helper.jdbc;

import com.appc.report.common.helper.JdbcUrlHelper;
import com.appc.report.model.DataSource;

public class SQLServerJdbcUrlHelper extends JdbcUrlHelper {
    @Override
    public String toJdbcUrl(DataSource ds) {
        return "jdbc:microsoft:sqlserver://<server_name>:<port>";
    }

    @Override
    public void loadUrl(String url, DataSource ds) {

    }
}
