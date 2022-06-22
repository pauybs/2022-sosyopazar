package com.mcavlak.sosyobazaar.models.entities;

import com.mcavlak.sosyobazaar.models.entities.abstracts.LocalDateTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.sql.Blob;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductPhoto extends LocalDateTimeEntity {
    @Lob
    private Blob photo;

    @ManyToOne
    private Product product;

    public static ProductPhoto create(Product product,Blob blob){
        ProductPhoto productPhoto=new ProductPhoto();
        productPhoto.setProduct(product);
        productPhoto.setPhoto(blob);
        return productPhoto;
    }
}
