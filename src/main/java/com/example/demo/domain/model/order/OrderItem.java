package com.example.demo.domain.model.order;

import com.example.demo.domain.model.BaseEntity;
import com.example.demo.domain.model.valueobject.Amount;
import com.example.demo.domain.model.valueobject.OrderId;
import com.example.demo.domain.model.valueobject.OrderItemId;
import com.example.demo.domain.model.valueobject.ProductId;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "ORDER_ITEMS")
public class OrderItem extends BaseEntity<OrderItemId> {

    private OrderId orderId;

//    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL, prefix = "PRODUCT_")
    private ProductId productId;

    private Integer quantity;

    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL, prefix = "SUB_TOTAL_")
    private Amount subTotalAmount;


    private OrderItem() {
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public ProductId getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Amount getSubTotalAmount() {
        return subTotalAmount;
    }
}

