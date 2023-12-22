package com.example.demo.infrastructure.converter;

import com.example.demo.domain.model.valueobject.OrderId;
import com.example.demo.domain.model.valueobject.OrderItemId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;

import java.sql.JDBCType;

@WritingConverter
public class OrderItemIdToLong implements Converter<OrderItemId, JdbcValue> {
    @Override
    public JdbcValue convert(OrderItemId source) {
        return JdbcValue.of(source.value(), JDBCType.BIGINT);
    }
}
