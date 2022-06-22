package com.mcavlak.sosyobazaar.exception;

import com.mcavlak.sosyobazaar.exception.base.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WrongRoleException extends BaseException {

    public WrongRoleException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public WrongRoleException(String message) {
        super(message);
    }
}
