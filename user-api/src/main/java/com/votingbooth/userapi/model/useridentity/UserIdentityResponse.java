package com.votingbooth.userapi.model.useridentity;

import java.util.UUID;

public class UserIdentityResponse {
    private UUID userId;

    public UserIdentityResponse(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }
}
