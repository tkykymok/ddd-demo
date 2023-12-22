package com.example.demo.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

import javax.annotation.processing.Generated;

public abstract class BaseEntity<ID> {
    @Id
    private ID id;

    protected BaseEntity() {}

    public ID getId() {
        return id;
    }
}
