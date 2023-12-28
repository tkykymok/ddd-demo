package com.example.demo.presentation.web.request.order;

import java.math.BigDecimal;

public record OrderItemRequest(
        Long orderItemId,
        Long productId,
        BigDecimal price,
        Integer quantity) {
}
