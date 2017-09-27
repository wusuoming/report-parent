package com.appc.report.common.enums;

public enum CollectionType {

    TABLE("table"),
    VIEW("view"),
//    COLLECTION("collection"),
    SQL("sql");

    private String code;
    private Integer inputType;

    CollectionType(String code) {
        this.code = code;
    }


    public static CollectionType getTypeByCode(String code) {
        for (CollectionType element : CollectionType.values()) {
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


