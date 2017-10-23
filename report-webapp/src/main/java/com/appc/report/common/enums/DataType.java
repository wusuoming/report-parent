package com.appc.report.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DataType {
    CHARACTER("1", "字符串"),
    INTEGER("2", "整数"),
    DECIMAL("3", "小数"),
    DATE("4", "日期"),
    TIME("5", "时间"),
    DATE_TIME("6", "日期时间");

    private String code;
    private String discription;

    DataType(String code, String discription) {
        this.code = code;
        this.discription = discription;
    }


    public static DataType getTypeByCode(String code) {
        for (DataType element : DataType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}


