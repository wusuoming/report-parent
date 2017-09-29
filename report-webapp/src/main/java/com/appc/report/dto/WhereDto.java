package com.appc.report.dto;

import com.appc.framework.mybatis.annotation.Where;
import com.appc.framework.mybatis.common.enums.ExpressionOperator;
import com.appc.framework.mybatis.common.enums.SqlLike;

import java.lang.annotation.Annotation;

public class WhereDto implements Where {

    private String name;
    private ExpressionOperator operator;
    private SqlLike sqlLike;

    private WhereDto(ExpressionOperator operator, SqlLike sqlLike) {
        this.operator = operator;
        this.sqlLike = sqlLike;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public ExpressionOperator operator() {
        return this.operator;
    }

    @Override
    public SqlLike sqlLike() {
        return this.sqlLike;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Where.class;
    }

    public static WhereDto create(ExpressionOperator operator, SqlLike sqlLike) {
        return new WhereDto(operator, sqlLike);

    }

    public void setName(String name) {
        this.name = name;
    }


}
