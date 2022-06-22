package com.mcavlak.sosyobazaar.exception;

import com.mcavlak.sosyobazaar.exception.base.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class IncorrectEntryException extends BaseException {

    public IncorrectEntryException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public IncorrectEntryException(String message) {
        super(message);
    }
}
