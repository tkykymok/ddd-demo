package com.example.demo.domain.queryservice.user;

import com.example.demo.application.usecase.user.UserOrderProduct;

import java.io.IOException;
import java.util.List;

public interface UserOrderProductQueryService {
    List<UserOrderProduct> findUserOrderProductDetails() throws IOException;
}
