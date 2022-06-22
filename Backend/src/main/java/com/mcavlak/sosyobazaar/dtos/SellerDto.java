package com.mcavlak.sosyobazaar.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mcavlak.sosyobazaar.models.entities.users.Customer;
import com.mcavlak.sosyobazaar.models.entities.users.User;
import com.mcavlak.sosyobazaar.utils.SecurityContextUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class SellerDto extends UserDto {

    private String storeName;
    private ProvinceDto province;
    private IndustryResponseDto industry;
    private int followersCount;
    private String webSite;
    private String phone;
    private String mail;
    private String fullAddress;
    private String longitude;
    private String latitude;
    private BigDecimal averageScore;

  @JsonProperty
    public boolean followed(){
              try {
                  User getCurrentUser = SecurityContextUtil.getCurrentUser();
                  Customer c = (Customer) getCurrentUser;
                  if(c != null){
                  return c.getFollows().stream().anyMatch(seller -> seller.getId() == this.getId());
                  }
              }catch (Exception e){
                  //throw e;
              }
        return false;
    }

}
