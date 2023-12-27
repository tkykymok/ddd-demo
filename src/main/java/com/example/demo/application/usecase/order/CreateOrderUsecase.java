package com.example.demo.application.usecase.order;

import com.example.demo.application.usecase.Usecase;
import com.example.demo.domain.model.order.Order;
import com.example.demo.domain.model.order.Product;
import com.example.demo.domain.model.valueobject.OrderItemId;
import com.example.demo.domain.model.valueobject.ProductId;
import com.example.demo.domain.model.valueobject.Quantity;
import com.example.demo.domain.model.valueobject.UserId;
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
public class CreateOrderUsecase extends Usecase<CreateOrderInput, Void> {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Void execute(CreateOrderInput input) {

        // 注文を生成する
        Order createdOrder = Order.create(new UserId(input.userId()));

        // 注文アイテムの商品IDを抽出する
        List<ProductId> productIds = extractProductIdsFromOrder(input);

        // 商品IDから商品をフェッチする
        Map<ProductId, Product> products = fetchProductsByIds(productIds);

        // 注文アイテムを追加する
        createdOrder.addOrderItems(input.orderItems(), products);

        // 注文を保存する
        orderRepository.insert(createdOrder);
        return null;
    }


    // 商品IDを注文から抽出するメソッド
    private List<ProductId> extractProductIdsFromOrder(CreateOrderInput input) {
        return input.orderItems().stream()
                .map(orderItem -> new ProductId(orderItem.productId()))
                .toList();
    }

    // 商品IDから商品をフェッチするメソッド
    private Map<ProductId, Product> fetchProductsByIds(List<ProductId> productIds) {
        return productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(product -> new ProductId(product.getId().value()), product -> product));
    }
}