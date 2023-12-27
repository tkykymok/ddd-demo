package com.example.demo.domain.model.order;

import com.example.demo.application.usecase.order.OrderItemInput;
import com.example.demo.domain.model.AggregateRoot;
import com.example.demo.domain.model.valueobject.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void updateVersion(VersionKey version) {
        this.version = version;
    }

    public void addOrderItems(List<OrderItemInput> orderItems, Map<ProductId, Product> products) {
        orderItems.forEach(orderItem -> {
            SeqNo seqNo = SeqNo.of(this.orderItems.size() + 1);
            OrderItem newOrderItem = OrderItem.create(
                    this.getId(),
                    seqNo,
                    products.get(ProductId.of(orderItem.productId())),
                    Quantity.of(orderItem.quantity())
            );
            this.orderItems.add(newOrderItem);
            calculateTotalAmount();
        });
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