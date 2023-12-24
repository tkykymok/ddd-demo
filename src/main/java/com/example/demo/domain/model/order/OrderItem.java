package com.example.demo.domain.model.order;

import com.example.demo.domain.model.SingleKeyBaseEntity;
import com.example.demo.domain.model.valueobject.*;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "ORDER_ITEMS")
public class OrderItem extends SingleKeyBaseEntity<OrderItemId> {
    private OrderId orderId;
    private SeqNo seqNo;
    private AggregateReference<Product, ProductId> productId;
    private Quantity quantity;
    private Amount subTotalAmount;

    private OrderItem() {
    }

    public static OrderItem create(OrderId orderId, SeqNo seqNo, Product product, Quantity quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.orderId = orderId;
        orderItem.seqNo = seqNo;
        orderItem.productId = AggregateReference.to(product.getId());
        orderItem.quantity = quantity;
        orderItem.subTotalAmount = Amount.calculateTotalFromPriceAndQuantity(product.getPrice(), quantity);
        return orderItem;
    }

    public static OrderItem update(OrderItemId id, OrderId orderId, SeqNo seqNo, Product product, Quantity quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.id = id;
        orderItem.orderId = orderId;
        orderItem.seqNo = seqNo;
        orderItem.productId = AggregateReference.to(product.getId());
        orderItem.quantity = quantity;
        orderItem.subTotalAmount = Amount.calculateTotalFromPriceAndQuantity(product.getPrice(), quantity);
        return orderItem;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public SeqNo getSeqNo() {
        return seqNo;
    }

    public ProductId getProductId() {
        return productId.getId();
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Amount getSubTotalAmount() {
        return subTotalAmount;
    }
}

