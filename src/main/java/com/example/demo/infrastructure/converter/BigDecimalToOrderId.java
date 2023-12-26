package com.example.demo.infrastructure.converter;

import com.example.demo.domain.model.valueobject.OrderId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.math.BigDecimal;

@ReadingConverter
public class BigDecimalToOrderId implements Converter<BigDecimal, OrderId> {
    @Override
    public OrderId convert(BigDecimal source) {
         return OrderId.of(source.longValue());
    }
}