package com.appc.report.model;

import lombok.Data;

@Data
public class Column {
    private String tableName;
    private Boolean isNullable;
    private String remarks;
    private String tableSchem;
    private int columnSize;
    private String typeName;
    private String columnName;
    private int decimalDigits;
    private Boolean isAutoincrement;


}
