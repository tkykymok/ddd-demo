package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.ValueObject;


public record VersionKey(Integer value) implements ValueObject {
    public static VersionKey of(Integer i) {
        return new VersionKey(i);
    }
}