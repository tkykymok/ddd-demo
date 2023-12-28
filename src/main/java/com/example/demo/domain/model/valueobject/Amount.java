package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.ValueObject;

import java.math.BigDecimal;


public record Amount(BigDecimal value) implements ValueObject {
    public static Amount of(BigDecimal value) {
        return new Amount(value);
    }

    public Amount add(Amount other) {
        return new Amount(this.value.add(other.value()));
    }

    public static Amount calculateTotalFromPriceAndQuantity(Price price, Quantity quantity) {
        if (price == null || quantity == null) throw new IllegalArgumentException("price or quantity is null");
        return new Amount(price.value().multiply(BigDecimal.valueOf(quantity.value())));
    }
}