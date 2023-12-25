package com.example.demo.domain.model.order;

import com.example.demo.domain.model.SingleKeyBaseEntity;
import com.example.demo.domain.model.valueobject.Price;
import com.example.demo.domain.model.valueobject.ProductId;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table(name = "products")
public class Product extends SingleKeyBaseEntity<ProductId> {
    private String name;
    private Price price;

    private Product() {}

}
