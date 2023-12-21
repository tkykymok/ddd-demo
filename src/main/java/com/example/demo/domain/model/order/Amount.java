package com.example.demo.domain.model.order;

import com.example.demo.domain.model.ValueObject;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;


public record Amount(@Column("AMOUNT") BigDecimal value) implements ValueObject {

    public Amount(BigDecimal value) {
        this.value = value != null ? value : BigDecimal.ZERO;
    }

    public Amount add(Amount other) {
        return new Amount(this.value.add(other.value()));
    }
}