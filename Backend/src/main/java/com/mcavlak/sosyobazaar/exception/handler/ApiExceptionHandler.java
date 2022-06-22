package com.mcavlak.sosyobazaar.exception.handler;

import com.mcavlak.sosyobazaar.exception.base.BaseException;
import com.mcavlak.sosyobazaar.exception.results.ErrorResult;
import com.mcavlak.sosyobazaar.exception.results.FieldErrorDataResult;
import com.mcavlak.sosyobazaar.exception.results.FieldErrorResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<FieldErrorResult> list = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            FieldErrorResult fieldErrorResult = new FieldErrorResult(fieldName, errorMessage);
            list.add(fieldErrorResult);
        });

        FieldErrorDataResult<List<FieldErrorResult>> fieldErrorDataResult = new FieldErrorDataResult<>(list);
        return new ResponseEntity<>(fieldErrorDataResult, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> baseException(BaseException e) {

        ErrorResult errorResult = new ErrorResult(e.getLocalizedMessage());
        return new ResponseEntity<>(errorResult, e.getHttpStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> AccessDeniedException(RuntimeException e) {

        ErrorResult errorResult = new ErrorResult("Yetkisiz Giriş");
        return new ResponseEntity<>(errorResult, HttpStatus.UNAUTHORIZED);
    }


}
