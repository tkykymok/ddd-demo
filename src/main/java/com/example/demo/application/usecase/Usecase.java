package com.example.demo.application.usecase;

public abstract class Usecase<T, R> {

    public abstract R execute(T input);

}
