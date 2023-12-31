package com.example.demo.application.usecase;

import java.io.IOException;

public abstract class Usecase<T, R> {

    public abstract R execute(T input) throws IOException;

}
