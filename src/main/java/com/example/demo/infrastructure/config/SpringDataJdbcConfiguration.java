package com.example.demo.infrastructure.config;

import com.example.demo.domain.model.task.TaskStatus;
import com.example.demo.domain.model.valueobject.*;
import com.example.demo.infrastructure.converter.*;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.Arrays;

@Configuration
@NonNullApi
public class SpringDataJdbcConfiguration extends AbstractJdbcConfiguration {

    @Override
    public JdbcCustomConversions jdbcCustomConversions() {
        return new JdbcCustomConversions(Arrays.asList(
                new EnumToInteger<TaskStatus>(),
                new GenericIdToBigInt<OrderId>(),
//                new GenericIdToBigInt<OrderItemId>(),
                new GenericIdToBigInt<ProductId>(),
                new GenericIdToBigInt<TaskId>(),
                new GenericIdToBigInt<SubTaskId>(),
                new GenericIdToBigInt<UserId>(),
                new AmountToDecimal(),
                new PriceToDecimal(),
                new QuantityToInteger(),
                new SeqNoToInteger(),
                new LongToOrderItemKey()
        ));
    }
}
