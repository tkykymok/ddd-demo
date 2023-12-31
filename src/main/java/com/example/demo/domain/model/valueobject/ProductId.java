package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.BaseId;
import com.example.demo.domain.model.ValueObject;

public record ProductId(Long value) implements BaseId<Long>, ValueObject {
    public static ProductId of(Long value) {
        return new ProductId(value);
    }
}
