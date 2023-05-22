CREATE TABLE users (
    id uuid NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(1000) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT false,
    role VARCHAR(32) NOT NULL,
    PRIMARY KEY(id)
);