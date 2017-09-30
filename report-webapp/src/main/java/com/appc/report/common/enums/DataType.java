package com.appc.report.common.enums;

public enum DataType {

    TABLE("table"),
    VIEW("view"),
    SQL("sql");

    private String code;
    private Integer inputType;

    DataType(String code) {
        this.code = code;
    }


    public static DataType getTypeByCode(String code) {
        for (DataType element : DataType.values()) {
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
}


