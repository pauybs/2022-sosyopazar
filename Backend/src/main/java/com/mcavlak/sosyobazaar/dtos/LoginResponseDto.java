package com.mcavlak.sosyobazaar.dtos;

import com.mcavlak.sosyobazaar.enums.Role;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    private final String username;
    private final String token;

    private final Role role;
    private final Long id;

    public LoginResponseDto(String username, String token,Role role,Long id) {
        this.username = username;
        this.token = token;
        this.role = role;
        this.id = id;
    }
}
