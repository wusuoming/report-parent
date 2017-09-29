package com.appc.report.common.enums;


import com.appc.framework.mybatis.common.enums.ExpressionOperator;
import com.appc.framework.mybatis.common.enums.SqlLike;
import com.appc.framework.mybatis.executor.criteria.Criteria;
import com.appc.framework.mybatis.executor.lazy.LazyProxy;
import com.appc.report.dto.WhereDto;

public enum FillterType {

    START_WITH(1, "开始以", WhereDto.create(ExpressionOperator.LIKE, SqlLike.RIGHT)),
    NO_START_WITH(2, "开始不是以", WhereDto.create(ExpressionOperator.NOT_LIKE, SqlLike.RIGHT)),
    LIKE(3, "包含", WhereDto.create(ExpressionOperator.LIKE, SqlLike.DEFAULT)),
    NO_LIKE(4, "不包含", WhereDto.create(ExpressionOperator.NOT_LIKE, SqlLike.DEFAULT)),
    END_WITH(5, "结束以", WhereDto.create(ExpressionOperator.LIKE, SqlLike.LEFT)),
    NO_END_WITH(6, "结束以不是以", WhereDto.create(ExpressionOperator.NOT_LIKE, SqlLike.LEFT)),
    BETWEEN(7, "介于", WhereDto.create(ExpressionOperator.BETWEEN, SqlLike.DEFAULT)),
    NOT_BETWEEN(8, "非介于", WhereDto.create(ExpressionOperator.NOT_BETWEEN, SqlLike.DEFAULT)),
    IS_BLANK(9, "空白的", WhereDto.create(ExpressionOperator.EQUAL, SqlLike.DEFAULT)),
    IS_NOT_BLANK(10, "非空白的", WhereDto.create(ExpressionOperator.NOT_EQUAL, SqlLike.DEFAULT)),
    EQUALS(11, "等于", WhereDto.create(ExpressionOperator.EQUAL, SqlLike.DEFAULT)),
    NO_EQUALS(12, "不等于", WhereDto.create(ExpressionOperator.NOT_EQUAL, SqlLike.DEFAULT)),
    IS_GREATER(13, "大于", WhereDto.create(ExpressionOperator.GREATER_THAN, SqlLike.DEFAULT)),
    IS_NOT_GREATER(14, "不大于", WhereDto.create(ExpressionOperator.EQUAL_AND_LESS_THAN, SqlLike.DEFAULT)),
    LESS(15, "小于", WhereDto.create(ExpressionOperator.LESS_THAN, SqlLike.DEFAULT)),
    NO_LESS(16, "不小于", WhereDto.create(ExpressionOperator.EQUAL_AND_GREATER_THAN, SqlLike.DEFAULT)),
    IS_NULL(17, "是null", WhereDto.create(ExpressionOperator.IS_NULL, SqlLike.DEFAULT)),
    IS_NOT_NULL(18, "不是null", WhereDto.create(ExpressionOperator.IS_NOT_NULL, SqlLike.DEFAULT)),;

    private WhereDto where;
    private Integer code;

    private String name;

    FillterType(Integer code, String name, WhereDto where) {
        this.code = code;
        this.name = name;
        this.where = where;
    }

    public static FillterType getTypeByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (FillterType element : FillterType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

    public void buildCriteria(Criteria criteria, String columnName, Object queryValue) {
//        if (this.equals(IS_BLANK) || this.equals(IS_NOT_BLANK)) {
//            queryValue
//        }
        LazyProxy.loadOperator(this.where, columnName, queryValue, criteria);
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public WhereDto getWhere() {
        return where;
    }
}