package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.ValueObject;


public record VersionKey(Long value) implements ValueObject {
    public static VersionKey of(Long value) {
        return new VersionKey(value);
    }

    public VersionKey increment() {
        return new VersionKey(this.value + 1);
    }
}