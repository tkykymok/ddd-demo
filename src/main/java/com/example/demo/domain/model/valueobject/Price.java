package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.ValueObject;

import java.math.BigDecimal;


public record Price(BigDecimal price) implements ValueObject {
    public Price(BigDecimal price) {
        this.price = price != null ? price : BigDecimal.ZERO;
    }
}