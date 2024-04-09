drop table if exists users;
drop table if exists roles;
drop table if exists user_roles;

CREATE TABLE users (
    username VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    password VARCHAR(255),
    phone_number VARCHAR(255),
    email VARCHAR(255),
    confirm_yn BOOLEAN,
    renew_password BOOLEAN
);

CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_type VARCHAR(255)
);

CREATE TABLE user_role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    role_id BIGINT,
    FOREIGN KEY (username) REFERENCES users(username),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE user_revision (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    timestamp BIGINT
);
