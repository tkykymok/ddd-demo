package com.example.demo.domain.model.order;

import com.example.demo.domain.model.AggregateRoot;
import com.example.demo.domain.model.valueobject.*;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "ORDERS")
public class Order extends AggregateRoot<OrderId>{
    private UserId userId;
    private LocalDate orderDate;
    private Amount totalAmount;
    @MappedCollection(idColumn = "ORDER_ID", keyColumn = "ORDER_ID")
    private List<OrderItem> orderItems;

    private Order() {}

    public static Order create(UserId userId) {
        Order order = new Order();
        order.userId = userId;
        order.orderDate = LocalDate.now();
        order.totalAmount = Amount.of(0);
        order.orderItems = new ArrayList<>();
        return order;
    }

    public void addOrderItem(Product product, Quantity quantity) {
        SeqNo seqNo = SeqNo.of(this.orderItems.size() + 1);

        OrderItem orderItem = OrderItem.create(this.getId(), seqNo, product, quantity);
        this.orderItems.add(orderItem);
        calculateTotalAmount();
    }

    private void calculateTotalAmount() {
        this.totalAmount = Amount.of(0);
        for (OrderItem orderItem : this.orderItems) {
            this.totalAmount = this.totalAmount.add(orderItem.getSubTotalAmount());
        }
    }

    public UserId getUserId() {
        return userId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Amount getTotalAmount() {
        return totalAmount;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
