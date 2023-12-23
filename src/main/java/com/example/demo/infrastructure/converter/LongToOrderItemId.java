package com.example.demo.infrastructure.converter;


import com.example.demo.domain.model.valueobject.OrderId;
import com.example.demo.domain.model.valueobject.OrderItemId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class LongToOrderItemId implements Converter<Long, OrderItemId> {

    @Override
    public OrderItemId convert(Long source) {
        // LongからOrderItemIdへの変換ロジック
        // この例では単純化のためにダミーのOrderItemIdを作成します
        return new OrderItemId(new OrderId(source), null);
    }
}
