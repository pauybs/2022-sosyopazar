package com.mcavlak.sosyobazaar.dtos;

import com.mcavlak.sosyobazaar.models.entities.users.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CustomerWithFollowsDto extends CustomerDto {
    private Set<SellerDto> follows;
}
