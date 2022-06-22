package com.mcavlak.sosyobazaar.models.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "il_id")
    private Long id;

    @Column(name = "il_adi")
    private String provinceName;

    public static Province create(Long id, String provinceName) {
        Province province = new Province();
        province.id = id;
        province.provinceName = provinceName;
        return province;
    }
    public static Province create( String provinceName) {
        Province province = new Province();
        province.provinceName = provinceName;
        return province;
    }
}
