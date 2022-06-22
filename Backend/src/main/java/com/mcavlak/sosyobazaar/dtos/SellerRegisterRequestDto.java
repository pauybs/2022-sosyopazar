package com.mcavlak.sosyobazaar.dtos;

import lombok.Getter;

@Getter
public class SellerRegisterRequestDto {
    private String username;
    private String password;
    private String storeName;
    private Long provinceId;
    private Long industryId;
}
