package com.votingbooth.userapi.service;

import com.votingbooth.userapi.dataaccess.UserIdentityRepository;
import com.votingbooth.userapi.dataaccess.UserRepository;
import com.votingbooth.userapi.model.user.UserNotFoundException;
import com.votingbooth.userapi.model.user.UserResponse;
import com.votingbooth.userapi.model.useridentity.AuthProvider;
import com.votingbooth.userapi.model.useridentity.UserIdentityRequest;
import com.votingbooth.userapi.model.useridentity.UserIdentityResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableConfigurationProperties
@ActiveProfiles("local")
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    void handleUserIdentity() {
        UserIdentityRequest request = new UserIdentityRequest(
                AuthProvider.GOOGLE,
                "1234",
                "1234@gmail.com"
        );
        UserIdentityResponse response = userService.handleUserIdentity(request);
        UUID userId = response.getUserId();

        UserIdentityResponse response1 = userService.handleUserIdentity(request);
        assertEquals(userId, response1.getUserId());

        UserIdentityRequest request2 = new UserIdentityRequest(
                AuthProvider.APPLE,
                "4321",
                "1234@gmail.com"
        );
        UserIdentityResponse response2 = userService.handleUserIdentity(request2);
        assertEquals(userId, response2.getUserId());

        UserIdentityRequest request3 = new UserIdentityRequest(
                AuthProvider.APPLE,
                "43210",
                "43210@gmail.com"
        );
        UserIdentityResponse response3 = userService.handleUserIdentity(request3);
        assertNotEquals(userId, response3.getUserId());
    }

    @Test
    void getUserId() {
        UserIdentityRequest request = new UserIdentityRequest(
                AuthProvider.GOOGLE,
                "12345",
                "12345@gmail.com"
        );
        UserIdentityResponse response = userService.handleUserIdentity(request);
        UUID userId = response.getUserId();

        UserResponse userResponse = userService.getUser(userId);
        assertEquals("12345@gmail.com", userResponse.getUser().getEmail());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUser(UUID.randomUUID());
        });
    }
}
