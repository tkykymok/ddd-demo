package com.example.demo.domain.repository.order;

import com.example.demo.domain.model.order.Order;
import com.example.demo.domain.model.valueobject.OrderId;
import com.example.demo.domain.model.valueobject.VersionKey;

public interface OrderRepository {
    Order findById(OrderId id);

    Order findByIdAndVersion(OrderId id, VersionKey version);

    void insert(Order order);

    void update(Order order);
}
