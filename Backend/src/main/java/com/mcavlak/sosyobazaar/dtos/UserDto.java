package com.mcavlak.sosyobazaar.dtos;

import com.mcavlak.sosyobazaar.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private Role role;
}
