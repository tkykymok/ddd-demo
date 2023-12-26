package com.example.demo.application.usecase.order;

import com.example.demo.application.usecase.Usecase;
import com.example.demo.domain.model.order.Order;
import com.example.demo.domain.model.order.Product;
import com.example.demo.domain.model.valueobject.OrderItemId;
import com.example.demo.domain.model.valueobject.ProductId;
import com.example.demo.domain.model.valueobject.Quantity;
import com.example.demo.domain.model.valueobject.UserId;
import com.example.demo.domain.repository.ProductRepository;
import com.example.demo.domain.repository.order.OrderItemRepository;
import com.example.demo.domain.repository.order.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CreateOrderUsecase extends Usecase<CreateOrderInput, Void> {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    // コンストラクタでリポジトリの初期化
    public CreateOrderUsecase(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Void execute(CreateOrderInput input) {

        // オーダーを生成し、永続化する
        Order createdOrder = createAndPersistOrder(input.userId());

        // オーダーアイテムの商品IDを抽出する
        List<ProductId> productIds = extractProductIdsFromOrder(input);

        // 商品IDから商品をフェッチする
        Map<ProductId, Product> products = fetchProductsByIds(productIds);

        // オーダーアイテムを追加し、全体を永続化する
        input.orderItems()
                .forEach(orderItem -> {
                    createdOrder.addOrderItem(
                            OrderItemId.of(orderItem.orderItemId()),
                            products.get(new ProductId(orderItem.productId())),
                            Quantity.of(orderItem.quantity())
                    );
                });

        orderRepository.save(createdOrder);
        return null;
    }

    // オーダーを生成し、永続化するメソッド
    private Order createAndPersistOrder(Long userId) {
        Order order = Order.create(new UserId(userId));
        return orderRepository.save(order);
    }


    // 商品IDをオーダから抽出するメソッド
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