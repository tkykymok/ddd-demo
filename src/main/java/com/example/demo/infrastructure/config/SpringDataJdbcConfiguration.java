package com.example.demo.infrastructure.config;

import com.example.demo.domain.model.order.Product;
import com.example.demo.domain.model.task.TaskStatus;
import com.example.demo.domain.model.valueobject.*;
import com.example.demo.infrastructure.converter.*;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
@NonNullApi
public class SpringDataJdbcConfiguration extends AbstractJdbcConfiguration {

    @Override
    public JdbcCustomConversions jdbcCustomConversions() {
        return new JdbcCustomConversions(Arrays.asList(
                new EnumToInteger<TaskStatus>(),
                new GenericIdToBigInt<OrderId, Long>(),
                new GenericIdToBigInt<OrderItemId, Long>(),
                new GenericIdToBigInt<ProductId, Long>(),
                new AggregateRefIdToBigInt<Product, ProductId>(),
                new GenericIdToBigInt<TaskId, Long>(),
                new GenericIdToBigInt<SubTaskId, Long>(),
                new GenericIdToBigInt<UserId, Long>(),
                new BigDecimalToOrderId(),
                new BigDecimalToOrderItemId(),
                new AmountToDecimal(),
                new PriceToDecimal(),
                new QuantityToInteger(),
                new SeqNoToInteger()
        ));
    }
}
