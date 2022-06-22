package com.mcavlak.sosyobazaar.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateContactDto {
    private String webSite;
    private String phone;
    private String mail;
    private String fullAddress;
    private String longitude;
    private String latitude;
    private Long provinceId;
}
