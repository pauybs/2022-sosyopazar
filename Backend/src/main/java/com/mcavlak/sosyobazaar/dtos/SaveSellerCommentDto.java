package com.mcavlak.sosyobazaar.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SaveSellerCommentDto {

    private Long sellerId;
    private String comment;
    private BigDecimal score;
}
