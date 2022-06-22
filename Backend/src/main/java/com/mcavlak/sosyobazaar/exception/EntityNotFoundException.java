package com.mcavlak.sosyobazaar.exception;

import com.mcavlak.sosyobazaar.exception.base.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
