package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.ValueObject;

import java.math.BigDecimal;


public record Amount(BigDecimal value) implements ValueObject {
    public Amount add(Amount other) {
        return new Amount(this.value.add(other.value()));
    }
}