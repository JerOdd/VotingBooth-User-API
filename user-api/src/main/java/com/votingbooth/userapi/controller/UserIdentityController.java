package com.votingbooth.userapi.controller;

import com.votingbooth.userapi.model.useridentity.UserIdentityRequest;
import com.votingbooth.userapi.model.useridentity.UserIdentityResponse;
import com.votingbooth.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user_identities")
public class UserIdentityController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserIdentityResponse> checkUserIdentity(UserIdentityRequest userIdentityRequest) {
        UserIdentityResponse userIdentityResponse = userService.handleUserIdentity(userIdentityRequest);
        return ResponseEntity.ok(userIdentityResponse);
    }
}
