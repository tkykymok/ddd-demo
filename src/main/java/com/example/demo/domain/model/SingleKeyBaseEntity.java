package com.example.demo.domain.model;

import com.example.demo.domain.model.valueobject.OrderId;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

import javax.annotation.processing.Generated;

public abstract class SingleKeyBaseEntity<ID> {
    protected ID id;

    protected SingleKeyBaseEntity() {}

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        if (this.id == null) {
            this.id = id;
        } else {
            throw new IllegalStateException("IDは一度だけ設定できます");
        }
    }

}
