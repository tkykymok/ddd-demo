//package com.example.demo.infrastructure.config;
//
//import java.util.Arrays;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
//
//import com.example.demo.infrastructure.converter.IntegerToTaskStatusConverter;
//import com.example.demo.infrastructure.converter.TaskStatusToIntegerConverter;
//
//@Configuration
//public class JdbcConfiguration {
//
//    @Bean
//    public JdbcCustomConversions jdbcCustomConversions() {
//        return new JdbcCustomConversions(
//                Arrays.asList(
//                        new TaskStatusToIntegerConverter(),
//                        new IntegerToTaskStatusConverter()
//                ));
//    }
//
//}
