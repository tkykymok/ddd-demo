package com.example.demo.infrastructure.converter;

import com.example.demo.domain.model.valueobject.Quantity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;

import java.sql.JDBCType;

@WritingConverter
public class QuantityToInteger implements Converter<Quantity, JdbcValue> {
    @Override
    public JdbcValue convert(Quantity source) {
        return JdbcValue.of(source.value(), JDBCType.INTEGER);
    }
}
