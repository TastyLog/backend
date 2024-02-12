package com.example.foodlog.global.config;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class CustomMySQLDialect extends MySQL8Dialect {

    /**
     * querydsl에서는 해당함수를 제공하지않기때문에 custom해서 mathch 함수 사용 시 해당 쿼리가 나가도록 설정
     */
    public CustomMySQLDialect() {
        super();
        registerFunction("match", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE, "MATCH(?1) AGAINST (?2 IN NATURAL LANGUAGE MODE)"));
        registerFunction("matchs", new SQLFunctionTemplate(StandardBasicTypes.DOUBLE, "MATCH(?1, ?2) AGAINST (?3 IN NATURAL LANGUAGE MODE)"));
    }
}
