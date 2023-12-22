package com.example.demo.infrastructure.converter;

import com.example.demo.domain.model.valueobject.Price;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;

import java.sql.JDBCType;

@WritingConverter
public class PriceToDecimal implements Converter<Price, JdbcValue> {
    @Override
    public JdbcValue convert(Price source) {
        return JdbcValue.of(source.value(), JDBCType.DECIMAL);
    }
}
