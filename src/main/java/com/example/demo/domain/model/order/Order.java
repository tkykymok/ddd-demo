package com.example.demo.domain.model.order;

import com.example.demo.domain.model.AggregateRoot;
import com.example.demo.domain.model.valueobject.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Order extends AggregateRoot<OrderId> {
    private UserId userId;
    private LocalDate orderDate;
    private Amount totalAmount;
    private VersionKey version;
    private List<OrderItem> orderItems;

    private Order() {
    }

    public static Order reconstruct(OrderId id, UserId userId, LocalDate orderDate, Amount totalAmount, VersionKey version, List<OrderItem> orderItems) {
        Order order = new Order();
        order.id = id;
        order.userId = userId;
        order.orderDate = orderDate;
        order.totalAmount = totalAmount;
        order.version = version;
        order.orderItems = orderItems;
        return order;
    }

    public static Order create(UserId userId) {
        Order order = new Order();
        order.userId = userId;
        order.orderDate = LocalDate.now();
        order.totalAmount = Amount.of(0);
        order.version = VersionKey.of(0L);
        order.orderItems = new ArrayList<>();
        return order;
    }

    public void addOrderItem(OrderItemId id, Product product, Quantity quantity) {
        SeqNo seqNo = SeqNo.of(this.orderItems.size() + 1);
        OrderItem orderItem = id.value() == null
                ? OrderItem.create(this.getId(), seqNo, product, quantity)
                : OrderItem.update(id, this.getId(), seqNo, product, quantity);
        this.orderItems.add(orderItem);
        calculateTotalAmount();
    }

    public void clearOrderItems() {
        this.orderItems.clear();
    }

    private void calculateTotalAmount() {
        this.totalAmount = Amount.of(0);
        for (OrderItem orderItem : this.orderItems) {
            this.totalAmount = this.totalAmount.add(orderItem.getSubTotalAmount());
        }
    }


}