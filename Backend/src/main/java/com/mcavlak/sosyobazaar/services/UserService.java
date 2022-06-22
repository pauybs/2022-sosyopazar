package com.mcavlak.sosyobazaar.services;

import com.mcavlak.sosyobazaar.models.entities.users.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

}
