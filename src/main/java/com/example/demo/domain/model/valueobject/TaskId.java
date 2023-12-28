package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.BaseId;
import com.example.demo.domain.model.ValueObject;

public record TaskId(Long value) implements BaseId<Long>, ValueObject {
    public static TaskId of(Long value) {
        if (value == null) {
            throw new IllegalArgumentException("TaskIdは必須です");
        }
        return new TaskId(value);
    }
}
