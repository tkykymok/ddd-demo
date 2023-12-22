package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.BaseId;
import com.example.demo.domain.model.ValueObject;

public record OrderItemId(Long value) implements BaseId<Long>, ValueObject {
}
