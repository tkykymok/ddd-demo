package com.example.demo.infrastructure.config;

import com.example.demo.infrastructure.converter.Enum2Integer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.Arrays;

@Configuration
public class SpringDataJdbcConfiguration extends AbstractJdbcConfiguration {

    @Override
    public JdbcCustomConversions jdbcCustomConversions() {
        return new JdbcCustomConversions(Arrays.asList(
                Enum2Integer.INSTANCE
//                new BigDecimalToMoneyConverter()
//                new IntegerToTaskStatusConverter(),
//                new Integer2Enum<TaskStatus>(TaskStatus.class)
        ));
    }
}
