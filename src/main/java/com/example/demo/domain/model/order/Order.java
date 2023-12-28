package com.example.demo.domain.model.order;

import com.example.demo.application.usecase.order.OrderItemInput;
import com.example.demo.domain.model.AggregateRoot;
import com.example.demo.domain.model.valueobject.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class Order extends AggregateRoot<OrderId> {
    private UserId userId;
    private LocalDate orderDate;
    private Amount totalAmount;
    private VersionKey version;
    private List<OrderItem> orderItems;

    private Order() {
    }

    // DBから取得したデータをドメインオブジェクトに変換する
    public static Order reconstruct(OrderId id, UserId userId, LocalDate orderDate, Amount totalAmount, VersionKey version, List<OrderItem> orderItems) {
        Order order = new Order();
        order.id = id;
        order.userId = userId;
        order.orderDate = orderDate;
        order.totalAmount = totalAmount;
        order.version = version;
        order.orderItems = orderItems;
        return order;
    }

    // ファクトリメソッド
    public static Order create(UserId userId) {
        Order order = new Order();
        order.userId = userId;
        order.orderDate = LocalDate.now();
        order.totalAmount = Amount.of(BigDecimal.ZERO);
        order.version = VersionKey.of(0L);
        order.orderItems = new ArrayList<>();
        return order;
    }

    // バージョンを更新する
    public void updateVersion(VersionKey version) {
        this.version = version;
    }

    // 注文アイテムを追加する
    public void addOrderItems(List<OrderItemInput> orderItems) {
        orderItems.forEach(orderItem -> {
            OrderItem newOrderItem = OrderItem.create(
                    this.getId(),
                    SeqNo.of(this.orderItems.size() + 1),
                    ProductId.of(orderItem.productId()),
                    Price.of(orderItem.price()),
                    Quantity.of(orderItem.quantity())
            );
            this.orderItems.add(newOrderItem);
            calculateTotalAmount();
        });
    }

    // 注文アイテムを削除する
    public void clearOrderItems() {
        this.orderItems.clear();
    }


    // 合計金額を計算する
    private void calculateTotalAmount() {
        this.totalAmount = Amount.of(BigDecimal.ZERO);
        for (OrderItem orderItem : this.orderItems) {
            this.totalAmount = this.totalAmount.add(orderItem.getSubTotalAmount());
        }
    }


}