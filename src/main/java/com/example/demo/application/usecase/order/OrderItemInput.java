package com.example.demo.application.usecase.order;

public record OrderItemInput(Long orderItemId, Long productId, Integer quantity) {
}
