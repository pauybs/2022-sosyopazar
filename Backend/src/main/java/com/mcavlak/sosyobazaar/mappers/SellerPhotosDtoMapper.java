package com.mcavlak.sosyobazaar.mappers;

import com.mcavlak.sosyobazaar.dtos.SellerPhotosDto;
import com.mcavlak.sosyobazaar.dtos.SellerWithoutDataDto;
import com.mcavlak.sosyobazaar.mappers.base.BaseMapper;
import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerPhotosDtoMapper extends BaseMapper<SellerPhotosDto, Seller> {
}
