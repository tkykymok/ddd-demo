package com.example.demo.domain.model.order;

import com.example.demo.domain.model.BaseEntity;
import com.example.demo.domain.model.valueobject.Price;
import com.example.demo.domain.model.valueobject.ProductId;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "PRODUCTS")
public class Product extends BaseEntity<ProductId> {
    private String name;

    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
    private Price price;

    private Product() {}

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }
}
