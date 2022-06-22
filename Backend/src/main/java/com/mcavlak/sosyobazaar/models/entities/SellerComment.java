package com.mcavlak.sosyobazaar.models.entities;

import com.mcavlak.sosyobazaar.models.entities.abstracts.LocalDateTimeEntity;
import com.mcavlak.sosyobazaar.models.entities.users.Customer;
import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import com.mcavlak.sosyobazaar.utils.SecurityContextUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SellerComment extends LocalDateTimeEntity {

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private Customer customer;

    private String comment;

    private BigDecimal score;

    public static SellerComment create(Seller seller, String comment, BigDecimal score) {
        SellerComment sellerComment = new SellerComment();
        sellerComment.seller = seller;
        sellerComment.customer = SecurityContextUtil.getCurrentCustomer();
        sellerComment.comment = comment;
        sellerComment.score = score;
        return sellerComment;
    }

}
