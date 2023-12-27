package com.example.demo.domain.model.order;

import com.example.demo.domain.model.SingleKeyBaseEntity;
import com.example.demo.domain.model.valueobject.Price;
import com.example.demo.domain.model.valueobject.ProductId;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Table;

@Getter
public class Product extends SingleKeyBaseEntity<ProductId> {
    private String name;
    private Price price;

    private Product() {}

    public static Product reconstruct(ProductId id, String name, Price price) {
        Product product = new Product();
        product.id = id;
        product.name = name;
        product.price = price;
        return product;
    }

}
