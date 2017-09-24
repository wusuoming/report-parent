package com.appc.report.common.enums;

public enum EncodingType {

    UTF8("utf8"), GBK("gbk"), ISO8859_1("iso8859_1"), GB2312("gb2312");

    private String code;

    EncodingType(String code) {
        this.code = code;
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
}


