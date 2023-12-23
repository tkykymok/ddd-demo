package com.example.demo.infrastructure.converter;


import com.example.demo.domain.model.valueobject.OrderId;
import com.example.demo.domain.model.valueobject.OrderItemKey;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class LongToOrderItemKey implements Converter<Long, OrderItemKey> {

    @Override
    public OrderItemKey convert(Long source) {
        return new OrderItemKey(new OrderId(source), null);
    }
}
