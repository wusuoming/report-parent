package com.appc.report.common.helper.jdbc;

import com.appc.report.common.helper.JdbcUrlHelper;
import com.appc.report.model.DataSource;
import org.springframework.util.StringUtils;

import java.util.Map;

public class MysqlJdbcUrlHelper extends JdbcUrlHelper {
    @Override
    public String toJdbcUrl(DataSource ds) {
        return "jdbc:mysql://" + ds.getSourceIp() + ":" + ds.getSourcePort() + "/" + ds.getDataName() + "?useUnicode=true&characterEncoding=" + (StringUtils.isEmpty(ds.getCharacterEncoding()) ? "utf8" : ds.getCharacterEncoding());
    }

    @Override
    public void loadUrl(String url, DataSource ds) {
        url = url.substring(url.indexOf("://") + 3);
        ds.setSourceIp(url.substring(0, url.indexOf(":")));
        url = url.substring(url.indexOf(":") + 1);
        ds.setSourcePort(url.substring(0, url.indexOf("/")));
        url = url.substring(url.indexOf("/") + 1);
        if (url.contains("?")) {
            ds.setDataName(url.substring(0, url.indexOf("?")));
            Map map = getParamsMap(url.substring(url.indexOf("?") + 1), "utf-8");
            String[] characterEncoding = (String[]) map.get("characterEncoding");
            if (characterEncoding != null) ds.setCharacterEncoding(characterEncoding[0]);
        } else {
            ds.setDataName(url);
        }

    }
}
