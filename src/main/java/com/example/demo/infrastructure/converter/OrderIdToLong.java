package com.example.demo.infrastructure.converter;

import com.example.demo.domain.model.valueobject.OrderId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;

import java.sql.JDBCType;

@WritingConverter
public class OrderIdToLong implements Converter<OrderId, JdbcValue> {
    @Override
    public JdbcValue convert(OrderId source) {
        return JdbcValue.of(source.value(), JDBCType.BIGINT);
    }
}
