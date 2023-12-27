package com.example.demo.domain.model;

import org.springframework.data.relational.core.mapping.Embedded;

public abstract class CompositeKeyBaseEntity<KEY> {
    protected KEY key;

    protected CompositeKeyBaseEntity() {}

    public KEY getKey() {
        return key;
    }
}

