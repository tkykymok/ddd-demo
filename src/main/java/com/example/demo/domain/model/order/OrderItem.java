package com.example.demo.domain.model.order;

import com.example.demo.domain.model.SingleKeyBaseEntity;
import com.example.demo.domain.model.valueobject.*;
import lombok.Getter;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table(name = "order_items")
public class OrderItem extends SingleKeyBaseEntity<OrderItemId> {
    private OrderId orderId;
    private SeqNo seqNo;
    private ProductId productId;
    private Quantity quantity;
    private Amount subTotalAmount;

    private OrderItem() {
    }

    public static OrderItem reconstruct(OrderItemId id, OrderId orderId, SeqNo seqNo, ProductId productId, Quantity quantity, Amount subTotalAmount) {
        OrderItem orderItem = new OrderItem();
        orderItem.id = id;
        orderItem.orderId = orderId;
        orderItem.seqNo = seqNo;
        orderItem.productId = productId;
        orderItem.quantity = quantity;
        orderItem.subTotalAmount = subTotalAmount;
        return orderItem;
    }

    public static OrderItem create(OrderId orderId, SeqNo seqNo, Product product, Quantity quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.orderId = orderId;
        orderItem.seqNo = seqNo;
        orderItem.productId = product.getId();
        orderItem.quantity = quantity;
        orderItem.subTotalAmount = Amount.calculateTotalFromPriceAndQuantity(product.getPrice(), quantity);
        return orderItem;
    }

    public static OrderItem update(OrderItemId id, OrderId orderId, SeqNo seqNo, Product product, Quantity quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.id = id;
        orderItem.orderId = orderId;
        orderItem.seqNo = seqNo;
        orderItem.productId = product.getId();
        orderItem.quantity = quantity;
        orderItem.subTotalAmount = Amount.calculateTotalFromPriceAndQuantity(product.getPrice(), quantity);
        return orderItem;
    }


}

