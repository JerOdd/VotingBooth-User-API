package com.votingbooth.userapi.dataaccess;

import com.votingbooth.userapi.model.useridentity.AuthProvider;
import com.votingbooth.userapi.model.useridentity.UserIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserIdentityRepository extends JpaRepository<UserIdentity, UUID> {

    Optional<UserIdentity> findByProviderAndProviderUserId(
        AuthProvider provider,
        String providerUserId
    );

}
