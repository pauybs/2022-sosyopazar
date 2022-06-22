package com.mcavlak.sosyobazaar.dtos;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AverageScoreDto {

    private final Long sellerId;
    private final BigDecimal averageScore;

    public AverageScoreDto(Long sellerId, BigDecimal averageScore) {
        this.sellerId = sellerId;
        this.averageScore = averageScore;
    }
}
