package com.votingbooth.userapi.service;

import com.votingbooth.userapi.dataaccess.UserIdentityRepository;
import com.votingbooth.userapi.dataaccess.UserRepository;
import com.votingbooth.userapi.model.User;
import com.votingbooth.userapi.model.useridentity.UserIdentity;
import com.votingbooth.userapi.model.useridentity.UserIdentityRequest;
import com.votingbooth.userapi.model.useridentity.UserIdentityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserIdentityRepository userIdentityRepository;

    public UserIdentityResponse checkUserIdentity(UserIdentityRequest userIdentityRequest) {
        UserIdentity userIdentity = userIdentityRepository.findByProviderAndProviderUserId(
                userIdentityRequest.getProvider(),
                userIdentityRequest.getProviderUserId()
        ).orElseGet(() -> {
            User user = userRepository.findByEmail(userIdentityRequest.getEmail()).orElseGet(() -> {
                User createdUser = new User(userIdentityRequest.getEmail());
                userRepository.save(createdUser);
                return createdUser;
            });
            UserIdentity createdUserIdentity = new UserIdentity(
                    userIdentityRequest.getProvider(),
                    userIdentityRequest.getProviderUserId(),
                    user.getId()
            );
            userIdentityRepository.save(createdUserIdentity);
            return createdUserIdentity;
        });
        return new UserIdentityResponse(userIdentity.getUserId());
    }
}
