package com.example.demo.application.usecase.order;

import java.util.List;

public record CreateOrderInput(Long userId, List<OrderItem> orderItems) {
    public record OrderItem(Long productId, Integer quantity) {
    }
}
