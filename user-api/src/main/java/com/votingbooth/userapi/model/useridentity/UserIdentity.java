package com.votingbooth.userapi.model.useridentity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "user_identities")
public class UserIdentity {
    @Id
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column(name = "provider_user_id", nullable = false)
    private String providerUserId;

    @Column(name = "user_id")
    private UUID userId;

    public UserIdentity() {}

    public UserIdentity(AuthProvider provider, String providerUserId, UUID userId) {
        this.id = UUID.randomUUID();
        this.provider = provider;
        this.providerUserId = providerUserId;
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }
}
