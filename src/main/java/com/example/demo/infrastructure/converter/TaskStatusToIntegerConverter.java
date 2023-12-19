package com.example.demo.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import com.example.demo.domain.model.task.TaskStatus;

@WritingConverter
public class TaskStatusToIntegerConverter implements Converter<TaskStatus, Integer> {
    @Override
    public Integer convert(TaskStatus source) {
        System.out.println("入ってる");
        return source == null ? null : source.getCode();
    }
}
