package com.example.demo.domain.repository.order;

import com.example.demo.domain.model.order.Order;
import com.example.demo.domain.model.valueobject.OrderId;

public interface OrderRepository {
    Order findById(OrderId id);

    Order findByIdAndVersion(OrderId id, Long version);

    void insert(Order order);

    void update(Order order);
}
