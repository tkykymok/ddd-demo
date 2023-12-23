package com.example.demo.domain.model.order;

import com.example.demo.domain.model.CompositeKeyBaseEntity;
import com.example.demo.domain.model.valueobject.*;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "ORDER_ITEMS")
public class OrderItem extends CompositeKeyBaseEntity<OrderItemKey> {
    private ProductId productId;
    private Quantity quantity;
    private Amount subTotalAmount;

    private OrderItem() {
    }

    public static OrderItem create(OrderId orderId, SeqNo seqNo, Product product, Quantity quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.key = new OrderItemKey(orderId, seqNo);
        orderItem.productId = product.getId();
        orderItem.quantity = quantity;
        orderItem.subTotalAmount = Amount.calculateTotalFromPriceAndQuantity(product.getPrice(), quantity);
        return orderItem;
    }

    public ProductId getProductId() {
        return productId;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Amount getSubTotalAmount() {
        return subTotalAmount;
    }
}

