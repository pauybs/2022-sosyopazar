package com.mcavlak.sosyobazaar.exception.results;

import lombok.Getter;

@Getter
public class FieldErrorDataResult<T> {

    private final T data;

    public FieldErrorDataResult(T data) {
        this.data = data;
    }
}

