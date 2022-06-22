package com.mcavlak.sosyobazaar.services;

import com.mcavlak.sosyobazaar.dtos.*;
import com.mcavlak.sosyobazaar.enums.Role;

public interface AccountService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    void registerCustomer(CustomerRegisterRequestDto customerRegisterRequestDto);

    void registerSeller(SellerRegisterRequestDto sellerRegisterRequestDto);

    UserDto getMe();

    Role getMyRole();
}
