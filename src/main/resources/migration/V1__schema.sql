DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS refresh_token;

CREATE TABLE user (
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL
);

CREATE TABLE role (
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE user_role(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
);

CREATE TABLE refresh_token(
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    token VARCHAR(255) NOT NULL UNIQUE,
    revoked BOOLEAN,
    date_created VARCHAR(255) NOT NULL
);