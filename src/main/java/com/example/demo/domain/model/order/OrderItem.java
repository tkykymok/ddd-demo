package com.example.demo.domain.model.order;

import com.example.demo.domain.model.CompositeKeyBaseEntity;
import com.example.demo.domain.model.valueobject.*;
import lombok.Getter;

@Getter
public class OrderItem extends CompositeKeyBaseEntity<OrderItemKey> {
    private ProductId productId;
    private Quantity quantity;
    private Amount subTotalAmount;

    private OrderItem() {
    }

    public static OrderItem reconstruct(OrderId orderId, SeqNo seqNo, ProductId productId, Quantity quantity, Amount subTotalAmount) {
        OrderItem orderItem = new OrderItem();
        orderItem.key = OrderItemKey.of(orderId, seqNo);
        orderItem.productId = productId;
        orderItem.quantity = quantity;
        orderItem.subTotalAmount = subTotalAmount;
        return orderItem;
    }

    public static OrderItem create(OrderId orderId, SeqNo seqNo, Product product, Quantity quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.key = OrderItemKey.of(orderId, seqNo);
        orderItem.productId = product.getId();
        orderItem.quantity = quantity;
        orderItem.subTotalAmount = Amount.calculateTotalFromPriceAndQuantity(product.getPrice(), quantity);
        return orderItem;
    }

    public static OrderItem update(OrderId orderId, SeqNo seqNo, Product product, Quantity quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.key = OrderItemKey.of(orderId, seqNo);
        orderItem.productId = product.getId();
        orderItem.quantity = quantity;
        orderItem.subTotalAmount = Amount.calculateTotalFromPriceAndQuantity(product.getPrice(), quantity);
        return orderItem;
    }


}

