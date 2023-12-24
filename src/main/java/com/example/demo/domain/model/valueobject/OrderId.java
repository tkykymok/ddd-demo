package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.BaseId;
import com.example.demo.domain.model.ValueObject;

public record OrderId(Long value) implements BaseId<Long>, ValueObject {
    public static OrderId of(Long value) {
        return new OrderId(value);
    }
}
