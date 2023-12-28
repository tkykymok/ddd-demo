package com.example.demo.application.usecase.order;

import com.example.demo.application.usecase.Usecase;
import com.example.demo.domain.model.order.Order;
import com.example.demo.domain.model.valueobject.OrderId;
import com.example.demo.domain.model.valueobject.VersionKey;
import com.example.demo.domain.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateOrderUsecase extends Usecase<UpdateOrderInput, Void> {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public Void execute(UpdateOrderInput input) {
        // 注文IDを使用して、注文を検索する
        Order order = orderRepository.findByIdAndVersion(OrderId.of(input.orderId()), input.version());
        if (order == null) {
            throw new RuntimeException("楽観的排他制御エラー");
        }

        // 注文のバージョンをインクリメントする
        order.updateVersion(VersionKey.of(input.version()).increment());

        // 注文アイテムをクリアして、新しいアイテムを追加する
        order.clearOrderItems();
        order.addOrderItems(input.orderItems());

        // 注文を更新する
        orderRepository.update(order);

        return null;
    }
}