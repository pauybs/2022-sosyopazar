package com.mcavlak.sosyobazaar.mappers;

import com.mcavlak.sosyobazaar.dtos.SellerCommentDto;
import com.mcavlak.sosyobazaar.mappers.base.BaseMapper;
import com.mcavlak.sosyobazaar.models.entities.SellerComment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerCommentMapper extends BaseMapper<SellerCommentDto, SellerComment> {
}
