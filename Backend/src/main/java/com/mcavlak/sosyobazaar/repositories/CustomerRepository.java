package com.mcavlak.sosyobazaar.repositories;

import com.mcavlak.sosyobazaar.models.entities.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
