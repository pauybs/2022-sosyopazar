package com.mcavlak.sosyobazaar.validations;

import com.mcavlak.sosyobazaar.exception.DuplicateStoreNameException;
import com.mcavlak.sosyobazaar.exception.DuplicateUsernameException;
import com.mcavlak.sosyobazaar.repositories.SellerRepository;
import com.mcavlak.sosyobazaar.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;


    public Validator(UserRepository userRepository, SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
    }

    public void usernameValidator(String username) {

        if (userRepository.findByUsername(username).isPresent()) {
            throw new DuplicateUsernameException("Bu username daha önceden alınmıştır. Lütfen başka username deneyiniz.");
        }
    }

    public void storeNameValidator(String storeName){

        if (sellerRepository.existsByStoreName(storeName)){
            throw new DuplicateStoreNameException("Bu mağaza adı daha önceden alınmıştır. Lütfen başka mağaza adı deneyiniz.");
        }
    }

}
