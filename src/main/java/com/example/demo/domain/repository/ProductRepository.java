package com.example.demo.domain.repository;

import com.example.demo.domain.model.order.Order;
import com.example.demo.domain.model.order.Product;
import com.example.demo.domain.model.valueobject.OrderId;
import com.example.demo.domain.model.valueobject.ProductId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepository extends ListCrudRepository<Product, ProductId> {

}
