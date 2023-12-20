package com.example.demo.presentation.controller;


import com.example.demo.domain.queryservice.user.UserOrderProductQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserOrderProductQueryService userOrderProductQueryService;

    public UserController(UserOrderProductQueryService userOrderProductQueryService) {
        this.userOrderProductQueryService = userOrderProductQueryService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getUsers() throws IOException {
        return ResponseEntity.ok(userOrderProductQueryService.findUserOrderProductDetails());
    }
}
