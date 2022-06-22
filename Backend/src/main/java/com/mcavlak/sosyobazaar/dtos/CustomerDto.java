package com.mcavlak.sosyobazaar.dtos;

import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CustomerDto extends UserDto{
    private String name;
}
