CREATE TABLE users (
   id         UUID         NOT NULL,
   email      VARCHAR(255) NOT NULL,
   created_at TIMESTAMP NOT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE user_identities (
    id               UUID         NOT NULL,
    provider         VARCHAR(255) NOT NULL,
    provider_user_id VARCHAR(255) NOT NULL,
    user_id          UUID,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);

