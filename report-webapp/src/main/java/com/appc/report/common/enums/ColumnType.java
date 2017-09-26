package com.appc.report.common.enums;


import java.sql.Types;

public enum ColumnType {
    /**
     * MySQL数据库
     */
    BIT(Types.BIT, "Mysql");


    private Integer code;

    private String prefix;

    ColumnType(Integer code, String prefix) {
        this.code = code;
        this.prefix = prefix;
    }

    public static ColumnType getTypeByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (ColumnType element : ColumnType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getPrefix() {
        return prefix;
    }
}