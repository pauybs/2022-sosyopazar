package com.mcavlak.sosyobazaar.mappers;

import com.mcavlak.sosyobazaar.dtos.IndustryResponseDto;
import com.mcavlak.sosyobazaar.mappers.base.BaseMapper;
import com.mcavlak.sosyobazaar.models.entities.Industry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IndustryMapper extends BaseMapper<IndustryResponseDto, Industry> {
}
