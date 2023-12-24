package com.example.demo.application.usecase.order;

import java.util.List;

public record CreateOrderInput(Long userId, List<OrderItemInput> orderItems) {
}
