package com.mcavlak.sosyobazaar.dtos;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class LoginRequestDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private boolean rememberMe;

}
