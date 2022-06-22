package com.mcavlak.sosyobazaar.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class SellerCommentDto {

    private CustomerDto customer;
    //private SellerDto seller;
    private String comment;
    private BigDecimal score;
    private LocalDateTime createdDateTime;
}
