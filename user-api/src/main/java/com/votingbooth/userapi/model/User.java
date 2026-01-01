package com.votingbooth.userapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String email;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public User() {}

    public User(String email) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.createdAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }
}
