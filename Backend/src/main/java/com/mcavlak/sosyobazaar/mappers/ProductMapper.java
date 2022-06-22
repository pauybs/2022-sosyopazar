package com.mcavlak.sosyobazaar.mappers;

import com.mcavlak.sosyobazaar.dtos.ProductDto;
import com.mcavlak.sosyobazaar.mappers.base.BaseMapper;
import com.mcavlak.sosyobazaar.models.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductDto, Product> {
}
