package com.example.demo.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;

import java.sql.JDBCType;

@WritingConverter
public class EnumToInteger<T extends Enum<T>> implements Converter<Enum<T>, JdbcValue> {
    @Override
    public JdbcValue convert(Enum<T> anEnum) {
        return JdbcValue.of(anEnum.ordinal(), JDBCType.INTEGER);
    }
}