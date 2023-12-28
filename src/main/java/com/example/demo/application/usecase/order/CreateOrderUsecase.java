package com.example.demo.application.usecase.order;

import com.example.demo.application.usecase.Usecase;
import com.example.demo.domain.model.order.Order;
import com.example.demo.domain.model.valueobject.UserId;
import com.example.demo.domain.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateOrderUsecase extends Usecase<CreateOrderInput, Void> {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public Void execute(CreateOrderInput input) {
        // 注文を生成する
        Order createdOrder = Order.create(new UserId(input.userId()));

        // 注文アイテムを追加する
        createdOrder.addOrderItems(input.orderItems());

        // 注文を保存する
        orderRepository.insert(createdOrder);

        return null;
    }
}