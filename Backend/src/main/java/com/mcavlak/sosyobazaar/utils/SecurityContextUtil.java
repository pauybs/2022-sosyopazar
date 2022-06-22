package com.mcavlak.sosyobazaar.utils;

import com.mcavlak.sosyobazaar.enums.Role;
import com.mcavlak.sosyobazaar.exception.WrongRoleException;
import com.mcavlak.sosyobazaar.models.CustomUserDetails;
import com.mcavlak.sosyobazaar.models.entities.users.Customer;
import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import com.mcavlak.sosyobazaar.models.entities.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityContextUtil {

    private SecurityContextUtil() {
    }

    public static User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUser();

    }

    public static Seller getCurrentSeller() {

        if (getCurrentUser() == null || getCurrentUser().getRole() != Role.ROLE_SELLER) {
            throw new WrongRoleException(HttpStatus.FORBIDDEN, "Yetkisiz işlem yapmaktasınız.");
        }
        return (Seller) getCurrentUser();

    }

    public static Customer getCurrentCustomer() {
        if (getCurrentUser() == null || getCurrentUser().getRole() != Role.ROLE_CUSTOMER) {
            throw new WrongRoleException(HttpStatus.FORBIDDEN, "Yetkisiz işlem yapmaktasınız.");
        }
        return (Customer) getCurrentUser();
    }


}
