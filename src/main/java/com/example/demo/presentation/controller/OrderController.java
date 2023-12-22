package com.example.demo.presentation.controller;

import com.example.demo.application.usecase.order.CreateOrderInput;
import com.example.demo.application.usecase.order.CreateOrderUsecase;
import com.example.demo.application.usecase.order.FetchOrderUsecase;
import com.example.demo.presentation.web.request.CreateOrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final FetchOrderUsecase fetchOrderUsecase;
    private final CreateOrderUsecase createOrderUsecase;

    public OrderController(FetchOrderUsecase fetchOrderUsecase, CreateOrderUsecase createOrderUsecase) {
        this.fetchOrderUsecase = fetchOrderUsecase;
        this.createOrderUsecase = createOrderUsecase;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderDetail(@PathVariable Long orderId) throws IOException {

        return ResponseEntity.ok(fetchOrderUsecase.execute(orderId));
    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest request) throws IOException {

        List<CreateOrderInput.OrderItem> orderItems = request.orderItems().stream().map(
                orderItem -> new CreateOrderInput.OrderItem(orderItem.productId(), orderItem.quantity())
        ).collect(Collectors.toList());

        CreateOrderInput input = new CreateOrderInput(1L, orderItems);
        return ResponseEntity.ok(createOrderUsecase.execute(input));
    }


}
