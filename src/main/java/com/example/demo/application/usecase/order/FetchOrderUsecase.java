package com.example.demo.application.usecase.order;

import com.example.demo.application.usecase.Usecase;
import com.example.demo.domain.model.order.Order;
import com.example.demo.domain.model.valueobject.OrderId;
import com.example.demo.domain.repository.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FetchOrderUsecase extends Usecase<Long, Order> {

    private final OrderRepository orderRepository;

    public FetchOrderUsecase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order execute(Long orderId) {
        Optional<Order> order = orderRepository.findById(new OrderId(orderId));
        return order.orElse(null);
    }
}
