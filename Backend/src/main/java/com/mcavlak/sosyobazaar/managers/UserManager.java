package com.mcavlak.sosyobazaar.managers;

import com.mcavlak.sosyobazaar.models.entities.users.User;
import com.mcavlak.sosyobazaar.repositories.UserRepository;
import com.mcavlak.sosyobazaar.services.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserManager implements UserService {

    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }
}
