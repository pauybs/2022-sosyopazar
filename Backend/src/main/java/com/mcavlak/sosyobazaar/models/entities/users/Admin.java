package com.mcavlak.sosyobazaar.models.entities.users;

import com.mcavlak.sosyobazaar.models.entities.abstracts.LocalDateTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@DiscriminatorValue("admin")
public class Admin extends LocalDateTimeEntity {
}
