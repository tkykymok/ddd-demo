package com.example.demo.presentation.web.request.order;

import java.util.List;

public record CreateOrderRequest(List<OrderItemRequest> orderItems) {

}
