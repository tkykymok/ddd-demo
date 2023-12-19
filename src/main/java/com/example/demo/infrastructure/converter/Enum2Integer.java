package com.example.demo.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;

import java.sql.JDBCType;

@WritingConverter
public enum Enum2Integer implements Converter<Enum, JdbcValue> {
    INSTANCE;

    @Override
    public JdbcValue convert(Enum anEnum) {
        return JdbcValue.of(anEnum.ordinal(), JDBCType.INTEGER);
    }
}
