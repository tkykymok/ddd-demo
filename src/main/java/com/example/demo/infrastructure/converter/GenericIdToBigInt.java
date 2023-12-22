package com.example.demo.infrastructure.converter;

import com.example.demo.domain.model.BaseId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;

import java.sql.JDBCType;

@WritingConverter
public class GenericIdToBigInt<T extends BaseId<Long>> implements Converter<T, JdbcValue> {
    @Override
    public JdbcValue convert(T source) {
        return JdbcValue.of(source.value(), JDBCType.BIGINT);
    }
}