package com.example.demo.presentation.web.request.order;

import java.util.List;

public record UpdateOrderRequest(
        Long version,
        List<OrderItemRequest> orderItems) {
}
