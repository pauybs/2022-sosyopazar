package com.mcavlak.sosyobazaar.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductWithoutSellerPhotoDto {

    private Long id;
    private String productName;
    private Double price;
    private String description;
    private SellerWithoutDataDto contentOwner;
    private List<Long> photoIdList;

}
