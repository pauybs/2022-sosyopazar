package com.mcavlak.sosyobazaar.mappers;

import com.mcavlak.sosyobazaar.dtos.PostDto;
import com.mcavlak.sosyobazaar.mappers.base.BaseMapper;
import com.mcavlak.sosyobazaar.models.entities.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper extends BaseMapper<PostDto, Post> {
    //todo useslari unutma
}
