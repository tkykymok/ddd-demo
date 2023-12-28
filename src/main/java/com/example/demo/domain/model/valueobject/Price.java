package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.ValueObject;

import java.math.BigDecimal;


public record Price(BigDecimal value) implements ValueObject {
    public static Price of(BigDecimal value) {
        return new Price(value);
    }
}