package com.example.demo.domain.model.valueobject;

import com.example.demo.domain.model.ValueObject;

import java.io.Serializable;

public record OrderItemId(
        OrderId orderId,
        SeqNo seqNo
) implements Serializable, ValueObject {
}
