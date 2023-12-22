package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.ValueObject;

import java.math.BigDecimal;


public record Price(BigDecimal value) implements ValueObject {
    public Price(BigDecimal value) {
        this.value = value != null ? value : BigDecimal.ZERO;
    }
}