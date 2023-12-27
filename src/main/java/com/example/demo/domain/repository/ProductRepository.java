package com.example.demo.domain.repository;

import com.example.demo.domain.model.order.Product;
import com.example.demo.domain.model.valueobject.ProductId;

import java.util.List;

public interface ProductRepository {

    List<Product> findAllById(List<ProductId> productIds);

}
