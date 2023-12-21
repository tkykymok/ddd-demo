package com.example.demo.domain.model.order;

import com.example.demo.domain.model.ValueObject;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;


public record Price(@Column("PRICE") BigDecimal value) implements ValueObject {
    public Price(BigDecimal value) {
        this.value = value != null ? value : BigDecimal.ZERO;
    }
}