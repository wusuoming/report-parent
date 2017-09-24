package com.appc.report.common.helper.jdbc;

import com.appc.report.common.helper.JdbcUrlHelper;
import com.appc.report.model.DataSource;

public class OracleJdbcUrlHelper extends JdbcUrlHelper {
    @Override
    public String toJdbcUrl(DataSource ds) {
        return "jdbc:oracle:thin:@<host>:<port>:<SID>";
    }

    @Override
    public void loadUrl(String url, DataSource ds) {

    }
}
