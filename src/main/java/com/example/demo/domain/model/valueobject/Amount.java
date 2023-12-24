package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.ValueObject;

import java.math.BigDecimal;


public record Amount(BigDecimal value) implements ValueObject {
    public static Amount of(double i) {
        return new Amount(BigDecimal.valueOf(i));
    }

    public Amount add(Amount other) {
        return new Amount(this.value.add(other.value()));
    }

    public static Amount calculateTotalFromPriceAndQuantity(Price price, Quantity quantity) {
        return new Amount(price.value().multiply(BigDecimal.valueOf(quantity.value())));
    }
}