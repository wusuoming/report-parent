package com.appc.report.common;

import junit.framework.TestCase;

import java.sql.*;

public class SqlUtilsTest extends TestCase {

    ResultSet results;
    ResultSetMetaData rsmd;
    DatabaseMetaData dma;
    Connection con;

    public void test() throws SQLException {
        String url = "jdbc:mysql://190.0.0.38:3306/medical?useUnicode=true&characterEncoding=utf8";
        try {
//加载 JDBC-ODBC 桥驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,"medical","Medical@2017");//连接数据库
            dma = con.getMetaData();//获取数据库的元数据
            System.out.println("Connected to:" + dma.getURL());
            System.out.println("Driver " + dma.getDriverName());
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            Statement stmt = con.createStatement();
            results = stmt.executeQuery("SELECT\n" +
                    "\t*\n" +
                    "FROM\n" +
                    "\t(\n" +
                    "\t\tSELECT\n" +
                    "\t\t\tdayTime ,\n" +
                    "\t\t\tsum(wxMoney / 100) wxMoney ,\n" +
                    "\t\t\tsum(alipayMoney / 100) alipayMoney ,\n" +
                    "\t\t\tsum(consumeMoney / 100) consumeMoney ,\n" +
                    "\t\t\tcount(IF(wxMoney != 0 , 1 , NULL)) wxCount ,\n" +
                    "\t\t\tcount(IF(alipayMoney != 0 , 1 , NULL)) alipayCount ,\n" +
                    "\t\t\tcount(IF(consumeMoney != 0 , 1 , NULL)) consumeCount ,\n" +
                    "\t\t\tsum(wxMoney / 100) + sum(alipayMoney / 100) - sum(consumeMoney / 100) settle ,\n" +
                    "\t\t\trecharge_status\n" +
                    "\t\tFROM\n" +
                    "\t\t\t(\n" +
                    "\t\t\t\tSELECT\n" +
                    "\t\t\t\t\tDATE_FORMAT(pay_time , '%Y-%m-%d') dayTime ,\n" +
                    "\t\t\t\t\tpay_money wxMoney ,\n" +
                    "\t\t\t\t\t0 alipayMoney ,\n" +
                    "\t\t\t\t\t0 consumeMoney ,\n" +
                    "\t\t\t\t\tCASE recharge_status\n" +
                    "\t\t\t\tWHEN '01' THEN\n" +
                    "\t\t\t\t\t'失败'\n" +
                    "\t\t\t\tWHEN '02' THEN\n" +
                    "\t\t\t\t\t'成功'\n" +
                    "\t\t\t\tELSE\n" +
                    "\t\t\t\t\t'失败'\n" +
                    "\t\t\t\tEND recharge_status\n" +
                    "\t\t\t\tFROM\n" +
                    "\t\t\t\t\tpay_order\n" +
                    "\t\t\t\tWHERE\n" +
                    "\t\t\t\t\tpay_status <> 00\n" +
                    "\t\t\t\tAND pay_type = 'WX'\n" +
                    "\t\t\t\tUNION ALL\n" +
                    "\t\t\t\t\tSELECT\n" +
                    "\t\t\t\t\t\tDATE_FORMAT(pay_time , '%Y-%m-%d') dayTime ,\n" +
                    "\t\t\t\t\t\t0 wxMoney ,\n" +
                    "\t\t\t\t\t\tpay_money alipayMoney ,\n" +
                    "\t\t\t\t\t\t0 consumeMoney ,\n" +
                    "\t\t\t\t\t\tCASE recharge_status\n" +
                    "\t\t\t\t\tWHEN '01' THEN\n" +
                    "\t\t\t\t\t\t'失败'\n" +
                    "\t\t\t\t\tWHEN '02' THEN\n" +
                    "\t\t\t\t\t\t'成功' ELSE '失败' END recharge_status FROM pay_order WHERE pay_status <> 00 AND pay_type = 'Alipay' UNION ALL SELECT DATE_FORMAT(pay_time , '%Y-%m-%d') dayTime ,\n" +
                    "\t\t\t\t\t\t0 wxMoney ,\n" +
                    "\t\t\t\t\t\t0 alipayMoney ,\n" +
                    "\t\t\t\t\t\tpay_money consumeMoney ,\n" +
                    "\t\t\t\t\t\tCASE recharge_status WHEN '01' THEN '失败' WHEN '02' THEN '成功' ELSE '失败' END recharge_status FROM pay_order WHERE pay_status <> 00 AND pay_type = 'Consume'\n" +
                    "\t\t\t) a GROUP BY recharge_status ,\n" +
                    "\t\t\tdayTime\n" +
                    "\t) b  ");
            ResultSetMetaData resultMetaData = results.getMetaData();
            int cols = resultMetaData.getColumnCount();
            String resultRow = "";
            for (int i = 1; i < cols; i++) {
                resultRow += resultMetaData.getColumnName(i) + ";";
                System.out.println(resultMetaData.getColumnName(i));
                System.out.println(resultMetaData.getColumnTypeName(i));
            }
            System.out.println(resultRow);
            while (results.next()) {
                resultRow = "";
                for (int i = 1; i < cols; i++) {
                    try {
                        resultRow += results.getString(i) + ";";
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println(resultRow);
            }
        } catch (Exception e) {
            System.out.println("query exception");
        } finally {
            results.close();
        }
    }


    public void testGetFieldForSql() throws Exception {
    }

}