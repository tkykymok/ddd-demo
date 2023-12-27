package com.example.demo.infrastructure.repository;

import com.example.demo.domain.model.order.Order;
import com.example.demo.domain.model.order.OrderItem;
import com.example.demo.domain.model.valueobject.*;
import com.example.demo.domain.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.infrastructure.jooq.Tables.ORDERS;
import static com.example.demo.infrastructure.jooq.Tables.ORDER_ITEMS;

@Repository
@RequiredArgsConstructor
public class JooqOrderRepository implements OrderRepository {

    private final DSLContext dsl;

    @Override
    public Order findById(OrderId id) {
        return findOrderAndItems(id, null);
    }

    @Override
    public Order findByIdAndVersion(OrderId id, Long version) {
        return findOrderAndItems(id, version);
    }

    @Override
    public Order insert(Order order) {
        // Order テーブルへの挿入と生成されたIDの取得
        Record insertedOrderRecord = dsl.insertInto(ORDERS)
                .set(ORDERS.USER_ID, order.getUserId().value())
                .set(ORDERS.ORDER_DATE, order.getOrderDate())
                .set(ORDERS.TOTAL_AMOUNT, order.getTotalAmount().value())
                .set(ORDERS.VERSION, order.getVersion().value())
                .returning(ORDERS.ID) // オートインクリメントされたIDを返す
                .fetchOne();

        if (insertedOrderRecord == null) {
            return null; // エラーハンドリングが必要です
        }

        OrderId generatedId = OrderId.of(insertedOrderRecord.getValue(ORDERS.ID));
        order.setId(generatedId); // 生成されたIDをセット

        // OrderItem のリストをループして、それぞれを ORDER_ITEMS テーブルに挿入
        for (OrderItem item : order.getOrderItems()) {
            int count = dsl.insertInto(ORDER_ITEMS)
                    .set(ORDER_ITEMS.ORDER_ID, generatedId.value())
                    .set(ORDER_ITEMS.SEQ_NO, item.getKey().seqNo().value())
                    .set(ORDER_ITEMS.PRODUCT_ID, item.getProductId().value())
                    .set(ORDER_ITEMS.QUANTITY, item.getQuantity().value())
                    .set(ORDER_ITEMS.SUB_TOTAL_AMOUNT, item.getSubTotalAmount().value())
                    .execute();

            if (count != 1) {
                return null; // エラーハンドリングが必要です
            }
        }

        return order; // 挿入されたOrderオブジェクトを返す
    }

    @Override
    public Order update(Order order) {
        // ORDERS テーブルのレコードを更新
        dsl.update(ORDERS)
                .set(ORDERS.USER_ID, order.getUserId().value())
                .set(ORDERS.ORDER_DATE, order.getOrderDate())
                .set(ORDERS.TOTAL_AMOUNT, order.getTotalAmount().value())
                .set(ORDERS.VERSION, order.getVersion().value())
                .where(ORDERS.ID.eq(order.getId().value()))
                .execute();

        // 関連するすべてのOrderItemsを削除
        dsl.deleteFrom(ORDER_ITEMS)
                .where(ORDER_ITEMS.ORDER_ID.eq(order.getId().value()))
                .execute();

        // 新しいOrderItemsを挿入
        for (OrderItem item : order.getOrderItems()) {
            dsl.insertInto(ORDER_ITEMS)
                    .set(ORDER_ITEMS.ORDER_ID, item.getKey().orderId().value())
                    .set(ORDER_ITEMS.SEQ_NO, item.getKey().seqNo().value())
                    .set(ORDER_ITEMS.PRODUCT_ID, item.getProductId().value())
                    .set(ORDER_ITEMS.QUANTITY, item.getQuantity().value())
                    .set(ORDER_ITEMS.SUB_TOTAL_AMOUNT, item.getSubTotalAmount().value())
                    .execute();
        }

        return order;
    }

    private Order findOrderAndItems(OrderId id, Long version) {
        Record orderRecord = fetchOrderRecord(id, version);
        if (orderRecord == null) {
            return null;
        }

        Result<Record> orderItemsRecords = dsl.select()
                .from(ORDER_ITEMS)
                .where(ORDER_ITEMS.ORDER_ID.eq(id.value()))
                .fetch();

        return recordToOrder(orderRecord, orderItemsRecords);
    }

    private Record fetchOrderRecord(OrderId id, Long version) {
        var selectCondition = dsl.select()
                .from(ORDERS)
                .where(ORDERS.ID.eq(id.value()));

        if (version != null) {
            selectCondition.and(ORDERS.VERSION.eq(version));
        }

        return selectCondition.fetchOne();
    }

    private Order recordToOrder(Record orderRecord, Result<Record> orderItemsRecords) {
        OrderId id = OrderId.of(orderRecord.get(ORDERS.ID));
        UserId userId = UserId.of(orderRecord.get(ORDERS.USER_ID));
        LocalDate orderDate = orderRecord.get(ORDERS.ORDER_DATE);
        Amount totalAmount = new Amount(orderRecord.get(ORDERS.TOTAL_AMOUNT));
        VersionKey version = VersionKey.of(orderRecord.get(ORDERS.VERSION));

        // OrderItemsの変換
        List<OrderItem> orderItems = orderItemsRecords.stream()
                .map(this::recordToOrderItem)
                .collect(Collectors.toList());

        return Order.reconstruct(id, userId, orderDate, totalAmount, version, orderItems);
    }

    private OrderItem recordToOrderItem(Record record) {
        OrderId orderId = new OrderId(record.get(ORDER_ITEMS.ORDER_ID));
        SeqNo seqNo = new SeqNo(record.get(ORDER_ITEMS.SEQ_NO));
        ProductId productId = new ProductId(record.get(ORDER_ITEMS.PRODUCT_ID));
        Quantity quantity = new Quantity(record.get(ORDER_ITEMS.QUANTITY));
        Amount subTotalAmount = new Amount(record.get(ORDER_ITEMS.SUB_TOTAL_AMOUNT));

        return OrderItem.reconstruct(orderId, seqNo, productId, quantity, subTotalAmount);

    }
}
