package com.example.demo.domain.model.order;

import com.example.demo.domain.model.BaseEntity;
import com.example.demo.domain.model.valueobject.*;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "ORDER_ITEMS")
public class OrderItem extends BaseEntity<OrderItemId> {
    private OrderId orderId;
    private ProductId productId;
    private Quantity quantity;
    private Amount subTotalAmount;

    private OrderItem() {
    }

    public OrderId getOrderId() {
        return orderId;
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

