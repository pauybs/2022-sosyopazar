package com.mcavlak.sosyobazaar.models.entities;

import com.mcavlak.sosyobazaar.models.entities.abstracts.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Industry extends BaseEntity {

    private String name;

    public static Industry create(String name) {
        Industry industry = new Industry();
        industry.name = name;
        return industry;
    }

}
