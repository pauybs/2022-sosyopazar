package com.mcavlak.sosyobazaar.mappers.base;

import com.mcavlak.sosyobazaar.dtos.SellerWithoutDataDto;
import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerWithoutDataMapper extends BaseMapper<SellerWithoutDataDto, Seller> {
}
