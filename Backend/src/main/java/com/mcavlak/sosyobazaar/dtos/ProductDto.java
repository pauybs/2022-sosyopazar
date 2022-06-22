package com.mcavlak.sosyobazaar.dtos;

import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import java.sql.Blob;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto extends BaseDto {

    private Long id;
    private String productName;
    private Double price;
    private String description;
    private SellerWithoutDataDto contentOwner;
    private List<Long> photoIdList;
}