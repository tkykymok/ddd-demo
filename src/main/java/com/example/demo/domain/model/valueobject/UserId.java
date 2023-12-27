package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.BaseId;
import com.example.demo.domain.model.ValueObject;

public record UserId(Long value) implements BaseId<Long>, ValueObject {
    public static UserId of(Long value) {
        return new UserId(value);
    }
}
