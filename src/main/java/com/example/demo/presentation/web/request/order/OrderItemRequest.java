package com.example.demo.presentation.web.request.order;

public record OrderItemRequest(Long orderItemId, Long productId, Integer quantity) {
}
