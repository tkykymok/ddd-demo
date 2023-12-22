package com.example.demo.presentation.controller;

import com.example.demo.application.usecase.order.FetchOrderUsecase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final FetchOrderUsecase fetchOrderUsecase;

    public OrderController(FetchOrderUsecase fetchOrderUsecase) {
        this.fetchOrderUsecase = fetchOrderUsecase;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getTask(@PathVariable Long orderId) throws IOException {

        return ResponseEntity.ok(fetchOrderUsecase.execute(orderId));
    }

}
