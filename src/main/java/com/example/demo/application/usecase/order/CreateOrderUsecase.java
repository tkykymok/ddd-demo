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

    public CreateOrderUsecase(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Void execute(CreateOrderInput input) {

        Order createdOrder = createAndPersistOrder(input.userId());

        List<ProductId> productIds = extractProductIdsFromOrder(input);

        Map<ProductId, Product> products = fetchProductsByIds(productIds);

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

    private Order createAndPersistOrder(Long userId) {
        Order order = Order.create(new UserId(userId));
        return orderRepository.save(order);
    }

    private List<ProductId> extractProductIdsFromOrder(CreateOrderInput input) {
        return input.orderItems().stream()
                .map(orderItem -> new ProductId(orderItem.productId()))
                .toList();
    }

    private Map<ProductId, Product> fetchProductsByIds(List<ProductId> productIds) {
        return productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(product -> new ProductId(product.getId().value()), product -> product));
    }
}