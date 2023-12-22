package com.example.demo.domain.repository.order;

import com.example.demo.domain.model.order.OrderItem;
import com.example.demo.domain.model.valueobject.OrderItemId;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderItemRepository extends ListCrudRepository<OrderItem, OrderItemId> {

}
