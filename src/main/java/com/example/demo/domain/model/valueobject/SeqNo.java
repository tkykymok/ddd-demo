package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.ValueObject;

public record SeqNo(Integer value) implements ValueObject {
    public static SeqNo of(Integer value) {
        return new SeqNo(value);
    }
}
