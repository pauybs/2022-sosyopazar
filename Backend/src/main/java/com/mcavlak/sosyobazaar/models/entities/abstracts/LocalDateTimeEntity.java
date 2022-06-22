package com.mcavlak.sosyobazaar.models.entities.abstracts;

import com.mcavlak.sosyobazaar.models.entities.abstracts.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter(AccessLevel.PROTECTED)
public abstract class LocalDateTimeEntity extends BaseEntity {

    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    @PrePersist
    protected void onCreate() {
        updatedDateTime = createdDateTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDateTime = LocalDateTime.now();
    }

}
