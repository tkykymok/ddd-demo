package com.example.demo.domain.queryservice.order;

import java.io.IOException;
import java.util.List;

public interface OrderQueryService {

    List<OrderDetailsResult> findOrderDetailsById(Long orderId) throws IOException;

}
