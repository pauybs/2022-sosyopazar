package com.mcavlak.sosyobazaar.services;

import com.mcavlak.sosyobazaar.dtos.FollowSellerDto;
import com.mcavlak.sosyobazaar.models.entities.users.Customer;

public interface CustomerService {

    Customer findDataById(Long id);

    void followSeller(FollowSellerDto followSellerDto);

}
