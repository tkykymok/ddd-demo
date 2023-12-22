package com.example.demo.infrastructure.converter;

import com.example.demo.domain.model.valueobject.Amount;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;

import java.sql.JDBCType;

@WritingConverter
public class AmountToDecimal implements Converter<Amount, JdbcValue> {
    @Override
    public JdbcValue convert(Amount source) {
        return JdbcValue.of(source.value(), JDBCType.DECIMAL);
    }
}
