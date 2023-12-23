package com.example.demo.domain.model;

import org.springframework.data.relational.core.mapping.Embedded;

public abstract class CompositeKeyBaseEntity<KEY> {
    @Embedded.Nullable
    protected KEY key;

    protected CompositeKeyBaseEntity() {}

    public KEY getKey() {
        return key;
    }
}

