package com.paulo.kafka.domain;

import com.paulo.kafka.domain.enums.PlayerMood;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@Slf4j
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PlayerMood mood;

    private Instant createdAt;

    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now().truncatedTo(ChronoUnit.MINUTES);
        updatedAt = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        log.info("Attempting to update player with id: {}", this.getId());
        updatedAt = Instant.now().truncatedTo(ChronoUnit.MINUTES);
        ;
    }
}
