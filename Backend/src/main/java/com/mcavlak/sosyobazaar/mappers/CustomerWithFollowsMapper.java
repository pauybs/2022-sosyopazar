package com.mcavlak.sosyobazaar.mappers;

import com.mcavlak.sosyobazaar.dtos.CustomerDto;
import com.mcavlak.sosyobazaar.dtos.CustomerWithFollowsDto;
import com.mcavlak.sosyobazaar.mappers.base.BaseMapper;
import com.mcavlak.sosyobazaar.models.entities.users.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerWithFollowsMapper extends BaseMapper<CustomerWithFollowsDto, Customer> {
}

