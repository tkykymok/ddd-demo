package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.BaseId;
import com.example.demo.domain.model.ValueObject;

import java.io.Serializable;

public record ProductId(Long value) implements BaseId<Long>, ValueObject {
}
