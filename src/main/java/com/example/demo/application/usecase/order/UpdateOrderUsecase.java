package com.example.demo.application.usecase.order;

import com.example.demo.application.usecase.Usecase;
import com.example.demo.domain.model.order.Order;
import com.example.demo.domain.model.order.Product;
import com.example.demo.domain.model.valueobject.*;
import com.example.demo.domain.repository.ProductRepository;
import com.example.demo.domain.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateOrderUsecase extends Usecase<UpdateOrderInput, Void> {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Void execute(UpdateOrderInput input) {

        // 注文IDを使用して、注文を検索する
        Order order = orderRepository.findByIdAndVersion(OrderId.of(input.orderId()), input.version());

        List<ProductId> productIds = extractProductIdsFromOrder(input);

        Map<ProductId, Product> products = fetchProductsByIds(productIds);

        // 注文のバージョンをインクリメントする
        order.updateVersion(VersionKey.of(input.version()).increment());

        // 注文アイテムをクリアして、新しいアイテムを追加する
        order.clearOrderItems();
        order.addOrderItems(input.orderItems(), products);

        // 注文を更新する
        orderRepository.update(order);
        return null;
    }

    private List<ProductId> extractProductIdsFromOrder(UpdateOrderInput input) {
        return input.orderItems().stream()
                .map(orderItem -> new ProductId(orderItem.productId()))
                .toList();
    }

    private Map<ProductId, Product> fetchProductsByIds(List<ProductId> productIds) {
        return productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(product
                        -> new ProductId(product.getId().value()), product -> product));
    }
}