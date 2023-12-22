package com.example.demo.domain.repository.order;

import com.example.demo.domain.model.order.Order;
import com.example.demo.domain.model.valueobject.OrderId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface OrderRepository extends ListCrudRepository<Order, OrderId> {

}
