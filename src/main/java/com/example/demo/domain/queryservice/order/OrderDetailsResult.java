package com.example.demo.domain.queryservice.order;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrderDetailsResult(Long id, LocalDate orderDate, BigDecimal totalAmount, String productName,
                                 BigDecimal productPrice, Integer quantity, BigDecimal subTotalAmount) {
}