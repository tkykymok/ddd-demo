//package com.example.demo.infrastructure.converter;
//
//import com.example.demo.domain.model.order.Money;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.data.convert.ReadingConverter;
//
//import java.math.BigDecimal;
//
//@ReadingConverter
//public class BigDecimalToMoneyConverter implements Converter<BigDecimal, Money> {
//    @Override
//    public Money convert(BigDecimal source) {
//        return source == null ? null : new Money(source);
//    }
//}