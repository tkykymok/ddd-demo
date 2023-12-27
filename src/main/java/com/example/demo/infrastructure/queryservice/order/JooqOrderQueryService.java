package com.example.demo.infrastructure.queryservice.order;

import com.example.demo.domain.queryservice.order.OrderDetailsResult;
import com.example.demo.domain.queryservice.order.OrderQueryService;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.infrastructure.jooq.Tables.*;

@Service
@RequiredArgsConstructor
public class JooqOrderQueryService implements OrderQueryService {
    private final DSLContext dsl;

    // 注文IDに基づいて注文詳細を取得するメソッド
    @Override
    public List<OrderDetailsResult> findOrderDetailsById(Long orderId) throws IOException {
        Result<Record> records = dsl.select()
                .from(ORDERS)
                .innerJoin(ORDER_ITEMS).on(ORDERS.ID.eq(ORDER_ITEMS.ORDER_ID))
                .innerJoin(PRODUCTS).on(ORDER_ITEMS.PRODUCT_ID.eq(PRODUCTS.ID))
                .where(ORDERS.ID.eq(orderId))
                .fetch();

        return records.stream()
                .map(this::recordToOrderDetailsResult)
                .collect(Collectors.toList());
    }

    private OrderDetailsResult recordToOrderDetailsResult(Record record) {
        // 以下はOrderDetailsResultオブジェクトの作成例です。
        // 実際のフィールドはOrderDetailsResultクラスの定義に依存します。
        Long orderId = record.getValue(ORDERS.ID);
        LocalDate orderDate = record.getValue(ORDERS.ORDER_DATE);
        BigDecimal totalAmount = record.getValue(ORDERS.TOTAL_AMOUNT);
        String productName = record.getValue(PRODUCTS.NAME);
        BigDecimal productPrice = record.getValue(PRODUCTS.PRICE);
        Integer quantity = record.getValue(ORDER_ITEMS.QUANTITY);
        BigDecimal subTotalAmount = record.getValue(ORDER_ITEMS.SUB_TOTAL_AMOUNT);

        return new OrderDetailsResult(orderId, orderDate, totalAmount, productName, productPrice, quantity, subTotalAmount);
    }
}
