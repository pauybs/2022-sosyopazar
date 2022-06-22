package com.mcavlak.sosyobazaar.models.entities.users;

import com.mcavlak.sosyobazaar.enums.Role;
import com.mcavlak.sosyobazaar.models.entities.abstracts.LocalDateTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userType", discriminatorType = DiscriminatorType.STRING)
public abstract class User extends LocalDateTimeEntity {

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
