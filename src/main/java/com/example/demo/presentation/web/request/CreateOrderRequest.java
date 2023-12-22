package com.example.demo.presentation.web.request;

import java.util.List;

public record CreateOrderRequest(List<OrderItem> orderItems) {
    public record OrderItem(Long productId, Integer quantity) {
    }
}
