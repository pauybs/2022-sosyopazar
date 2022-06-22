package com.mcavlak.sosyobazaar.exception;

import com.mcavlak.sosyobazaar.exception.base.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DuplicateStoreNameException extends BaseException {

    public DuplicateStoreNameException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public DuplicateStoreNameException(String message) {
        super(message);
    }
}
