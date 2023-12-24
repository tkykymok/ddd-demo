package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.BaseId;
import com.example.demo.domain.model.ValueObject;

public record SubTaskId(Long value) implements BaseId<Long>, ValueObject {
    public static SubTaskId of(Long value) {
        return new SubTaskId(value);
    }
}
