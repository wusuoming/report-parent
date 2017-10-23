package com.appc.report.dto;

import com.appc.report.common.enums.DataType;
import lombok.Data;

@Data
public class ColumnDto {
    private String tableName;
    private Boolean isNullable;
    private String remarks;
    private String tableSchem;
    private Integer columnSize;
    private String typeName;
    private DataType dataType;
    private String columnName;
    private Integer decimalDigits;
    private Boolean isAutoincrement;


}
