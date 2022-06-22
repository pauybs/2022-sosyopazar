package com.mcavlak.sosyobazaar.models.entities;

import com.mcavlak.sosyobazaar.dtos.ProductSaveDto;
import com.mcavlak.sosyobazaar.models.entities.abstracts.LocalDateTimeEntity;
import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import com.mcavlak.sosyobazaar.utils.SecurityContextUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends LocalDateTimeEntity {

    private String productName;
    private Double price;
    private String description;

    @ManyToOne
    private Seller contentOwner;

    public static Product create(ProductSaveDto productSaveDto) {
        Product product = new Product();
        product.productName = productSaveDto.getProductName();
        product.price= productSaveDto.getPrice();
        product.description= productSaveDto.getDescription();
        product.contentOwner = SecurityContextUtil.getCurrentSeller();
        return product;
    }

}
