package com.votingbooth.userapi.model.user;

public class UserResponse {
    private User user;

    public UserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
