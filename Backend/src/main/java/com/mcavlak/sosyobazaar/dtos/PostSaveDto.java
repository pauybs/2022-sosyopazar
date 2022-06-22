package com.mcavlak.sosyobazaar.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;

@Getter
@Setter
public class PostSaveDto {

    @Lob
    private String content;

}
