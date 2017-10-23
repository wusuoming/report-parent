package com.appc.report.common.enums;


import java.sql.Types;

public enum ColumnType {

    BIT(Types.BIT, DataType.INTEGER),
    TINYINT(Types.TINYINT, DataType.INTEGER),
    SMALLINT(Types.SMALLINT, DataType.INTEGER),
    INTEGER(Types.INTEGER, DataType.INTEGER),
    BIGINT(Types.BIGINT, DataType.INTEGER),
    FLOAT(Types.FLOAT, DataType.DECIMAL),
    REAL(Types.REAL, DataType.DECIMAL),
    DOUBLE(Types.DOUBLE, DataType.DECIMAL),
    NUMERIC(Types.NUMERIC, DataType.DECIMAL),
    DECIMAL(Types.DECIMAL, DataType.DECIMAL),
    CHAR(Types.CHAR, DataType.CHARACTER),
    VARCHAR(Types.VARCHAR, DataType.CHARACTER),
    LONGVARCHAR(Types.LONGVARCHAR, DataType.CHARACTER),
    DATE(Types.DATE, DataType.DATE),
    TIME(Types.TIME, DataType.TIME),
    TIMESTAMP(Types.TIMESTAMP, DataType.DATE_TIME),
    BINARY(Types.BINARY, DataType.CHARACTER),
    VARBINARY(Types.VARBINARY, DataType.CHARACTER),
    LONGVARBINARY(Types.LONGVARBINARY, DataType.CHARACTER),
    BLOB(Types.BLOB, DataType.CHARACTER),
    CLOB(Types.CLOB, DataType.CHARACTER),
    ROWID(Types.ROWID, DataType.CHARACTER),
    NCHAR(Types.NCHAR, DataType.CHARACTER),
    NVARCHAR(Types.NVARCHAR, DataType.CHARACTER),
    LONGNVARCHAR(Types.LONGNVARCHAR, DataType.CHARACTER),
    NCLOB(Types.NCLOB, DataType.CHARACTER),
    SQLXML(Types.SQLXML, DataType.CHARACTER),
    TIME_WITH_TIMEZONE(Types.TIME_WITH_TIMEZONE, DataType.TIME),
    TIMESTAMP_WITH_TIMEZONE(Types.TIMESTAMP_WITH_TIMEZONE, DataType.DATE_TIME);


    private Integer code;

    private DataType dataType;

    ColumnType(Integer code, DataType dataType) {
        this.code = code;
        this.dataType = dataType;
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
        return ColumnType.VARCHAR;
    }

    public Integer getCode() {
        return code;
    }

    public DataType getDataType() {
        return dataType;
    }
}