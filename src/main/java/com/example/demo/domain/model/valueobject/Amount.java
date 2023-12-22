package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.ValueObject;

import java.math.BigDecimal;


public record Amount(BigDecimal amount) implements ValueObject {

    public Amount(BigDecimal amount) {
        this.amount = amount != null ? amount : BigDecimal.ZERO;
    }

    public Amount add(Amount other) {
        return new Amount(this.amount.add(other.amount()));
    }
}