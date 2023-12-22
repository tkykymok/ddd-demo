package com.example.demo.infrastructure.converter;

import com.example.demo.domain.model.valueobject.TaskId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;

import java.sql.JDBCType;

@WritingConverter
public class TaskIdToLong implements Converter<TaskId, JdbcValue> {
    @Override
    public JdbcValue convert(TaskId source) {
        return JdbcValue.of(source.value(), JDBCType.BIGINT);
    }
}
