package com.mcavlak.sosyobazaar.services;

import com.mcavlak.sosyobazaar.dtos.AverageScoreDto;
import com.mcavlak.sosyobazaar.dtos.SaveSellerCommentDto;
import com.mcavlak.sosyobazaar.dtos.SellerCommentDto;

import java.util.List;

public interface SellerCommentService {

    void save(SaveSellerCommentDto saveSellerCommentDto);

    List<SellerCommentDto> findAllBySellerId(Long sellerId);

    AverageScoreDto getAverageScoreBySellerId(Long sellerId);

}
