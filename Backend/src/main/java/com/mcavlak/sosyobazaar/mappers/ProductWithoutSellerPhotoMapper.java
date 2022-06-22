package com.mcavlak.sosyobazaar.mappers;

import com.mcavlak.sosyobazaar.dtos.ProductWithoutSellerPhotoDto;
import com.mcavlak.sosyobazaar.mappers.base.BaseMapper;
import com.mcavlak.sosyobazaar.models.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductWithoutSellerPhotoMapper extends BaseMapper<ProductWithoutSellerPhotoDto, Product> {
}
