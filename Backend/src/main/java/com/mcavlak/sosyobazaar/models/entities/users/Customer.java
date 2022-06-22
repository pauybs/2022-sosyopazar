package com.mcavlak.sosyobazaar.models.entities.users;

import com.mcavlak.sosyobazaar.enums.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@DiscriminatorValue("customer")
public class Customer extends User {

    private String name;

    @ManyToMany(mappedBy = "followers",cascade = CascadeType.PERSIST)
    private Set<Seller> follows;

    protected Customer() {
        this.setRole(Role.ROLE_CUSTOMER);
    }

    public static Customer create(String username, String password,String name) {
        Customer customer = new Customer();
        customer.name = name;
        customer.setUsername(username);
        customer.setPassword(password);
        return customer;
    }

}
