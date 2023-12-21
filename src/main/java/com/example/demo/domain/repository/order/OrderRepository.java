package com.example.demo.domain.repository.order;

import com.example.demo.domain.model.order.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
