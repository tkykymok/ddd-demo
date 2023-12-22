package com.example.demo.domain.model.order;

import com.example.demo.domain.model.AggregateRoot;
import com.example.demo.domain.model.valueobject.Amount;
import com.example.demo.domain.model.valueobject.OrderId;
import com.example.demo.domain.model.valueobject.UserId;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.List;

@Table(name = "ORDERS")
public class Order extends AggregateRoot<OrderId>{
    private UserId userId;
    private LocalDate orderDate;
    private Amount totalAmount;
    @MappedCollection(idColumn = "ORDER_ID", keyColumn = "ID")
    private List<OrderItem> orderItems;

    private Order() {}

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

}