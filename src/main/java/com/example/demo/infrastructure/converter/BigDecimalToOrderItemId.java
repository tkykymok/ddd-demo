package com.example.demo.infrastructure.converter;

import com.example.demo.domain.model.valueobject.OrderId;
import com.example.demo.domain.model.valueobject.OrderItemId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.math.BigDecimal;

@ReadingConverter
public class BigDecimalToOrderItemId implements Converter<BigDecimal, OrderItemId> {
    @Override
    public OrderItemId convert(BigDecimal source) {
         return OrderItemId.of(source.longValue());
    }
}