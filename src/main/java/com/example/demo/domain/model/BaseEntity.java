package com.example.demo.domain.model;

import org.springframework.data.annotation.Id;

public abstract class BaseEntity {
    @Id
    private Long id;

    protected BaseEntity() {}

    public Long getId() {
        return id;
    }
}
