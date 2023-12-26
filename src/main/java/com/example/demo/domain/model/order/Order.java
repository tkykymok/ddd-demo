package com.example.demo.domain.model.order;

import com.example.demo.domain.model.AggregateRoot;
import com.example.demo.domain.model.valueobject.*;
import lombok.Getter;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Table(name = "orders") // "orders"という名前のテーブルとマッピングします
public class Order extends AggregateRoot<OrderId>{
    private UserId userId; // ユーザーID
    private LocalDate orderDate; // 注文日
    private Amount totalAmount; // 合計金額
    @Version
    private Long version; // バージョン
    @MappedCollection(idColumn = "order_id") // "order_id"を識別子とするマッピングコレクション
    private Set<OrderItem> orderItems; // 注文アイテムのセット

    private Order() {} // プライベートコンストラクタ

    public static Order create(UserId userId) { // Orderを新規作成するスタティクメソッド
        Order order = new Order();
        order.userId = userId; // ユーザーIDを設定します
        order.orderDate = LocalDate.now(); // 注文日に現在の日付を設定します
        order.totalAmount = Amount.of(0); // 合計金額を0に初期設定します
        order.orderItems = new LinkedHashSet<>(); // OrderItemのセットを新規作成します
        return order; // Orderを返します
    }

    public void addOrderItem(OrderItemId id, Product product, Quantity quantity) { // OrderItemを追加するメソッド
        SeqNo seqNo = SeqNo.of(this.orderItems.size() + 1); // SeqNoを作成します

        // 注文アイテムを作成または更新します
        OrderItem orderItem = id.value() == null
                ? OrderItem.create(this.getId(), seqNo, product, quantity)
                : OrderItem.update(id, this.getId(), seqNo, product, quantity);
        this.orderItems.add(orderItem); // 注文アイテムを追加します
        calculateTotalAmount(); // 合計金額を計算します
    }

    public void clearOrderItems() { // 注文アイテムを全てクリアするメソッド
        this.orderItems.clear(); // 注文アイテムをクリアします
    }

    private void calculateTotalAmount() { // 合計金額を計算するメソッド
        this.totalAmount = Amount.of(0); // 合計金額を0に初期化します
        for (OrderItem orderItem : this.orderItems) { // 注文アイテムすべてに対して
            this.totalAmount = this.totalAmount.add(orderItem.getSubTotalAmount()); // 各アイテムの小計を合計金額に加算します
        }
    }

}