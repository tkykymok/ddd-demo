package com.example.demo.domain.model;


import java.io.Serializable;

public interface BaseId<T> extends Serializable {
    T value();
}
