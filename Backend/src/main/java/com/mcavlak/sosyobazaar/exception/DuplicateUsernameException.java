package com.mcavlak.sosyobazaar.exception;

import com.mcavlak.sosyobazaar.exception.base.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DuplicateUsernameException extends BaseException {

    public DuplicateUsernameException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public DuplicateUsernameException(String message) {
        super(message);
    }
}
