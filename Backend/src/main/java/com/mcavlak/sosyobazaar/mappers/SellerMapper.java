package com.mcavlak.sosyobazaar.mappers;

import com.mcavlak.sosyobazaar.dtos.SellerDto;
import com.mcavlak.sosyobazaar.mappers.base.BaseMapper;
import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerMapper extends BaseMapper<SellerDto, Seller> {
}
