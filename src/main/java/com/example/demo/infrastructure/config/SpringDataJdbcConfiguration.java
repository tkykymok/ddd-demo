package com.example.demo.infrastructure.config;

import com.example.demo.infrastructure.converter.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.Arrays;

@Configuration
public class SpringDataJdbcConfiguration extends AbstractJdbcConfiguration {

    @Override
    public JdbcCustomConversions jdbcCustomConversions() {
        return new JdbcCustomConversions(Arrays.asList(
                Enum2Integer.INSTANCE,
                new OrderIdToLong(),
                new OrderItemIdToLong(),
                new ProductIdToLong(),
                new TaskIdToLong(),
                new SubTaskIdToLong()
        ));
    }
}
