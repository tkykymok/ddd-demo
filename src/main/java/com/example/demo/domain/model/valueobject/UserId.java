package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.ValueObject;

import java.io.Serializable;

public record UserId(Long value) implements ValueObject, Serializable {
}
