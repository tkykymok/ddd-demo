package com.example.demo.domain.model.order;

import com.example.demo.domain.model.BaseEntity;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "ORDER_ITEMS")
public class OrderItem extends BaseEntity {
    private Long orderId;

    private Long productId;

    private Integer quantity;

    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL, prefix = "SUB_TOTAL_")
    private Amount subTotalAmount;


    private OrderItem() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Amount getSubTotalAmount() {
        return subTotalAmount;
    }
}

