package com.example.demo.domain.model.order;

import com.example.demo.domain.model.SingleKeyBaseEntity;
import com.example.demo.domain.model.valueobject.Price;
import com.example.demo.domain.model.valueobject.ProductId;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "PRODUCTS")
public class Product extends SingleKeyBaseEntity<ProductId> {
    private String name;
    private Price price;

    private Product() {}

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }
}
