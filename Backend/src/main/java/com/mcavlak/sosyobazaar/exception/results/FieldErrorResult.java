package com.mcavlak.sosyobazaar.exception.results;

import lombok.Getter;

@Getter
public class FieldErrorResult extends ErrorResult{

    private final String field;

    public FieldErrorResult(String field, String message) {
        super(message);
        this.field = field;
    }

}
