package com.example.demo.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import com.example.demo.domain.model.task.TaskStatus;

import java.util.Objects;

@ReadingConverter
public class IntegerToTaskStatusConverter implements Converter<Integer, TaskStatus> {
    @Override
    public TaskStatus convert(Integer source) {
        for (TaskStatus status : TaskStatus.values()) {
            if (Objects.equals(status.getCode(), source)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + source);
    }
}