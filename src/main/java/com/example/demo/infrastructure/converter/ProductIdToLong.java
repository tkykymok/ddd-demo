package com.example.demo.infrastructure.converter;

import com.example.demo.domain.model.valueobject.OrderItemId;
import com.example.demo.domain.model.valueobject.ProductId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;

import java.sql.JDBCType;

@WritingConverter
public class ProductIdToLong implements Converter<ProductId, JdbcValue> {
    @Override
    public JdbcValue convert(ProductId source) {
        return JdbcValue.of(source.value(), JDBCType.BIGINT);
    }
}
