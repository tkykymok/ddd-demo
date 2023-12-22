//package com.example.demo.infrastructure.converter;
//
//import com.example.demo.domain.model.valueobject.OrderId;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.data.convert.ReadingConverter;
//
//@ReadingConverter
//public class LongToOrderIdConverter implements Converter<Long, OrderId> {
//    @Override
//    public OrderId convert(Long source) {
//        return source == null ? null : new OrderId(source);
//    }
//}
