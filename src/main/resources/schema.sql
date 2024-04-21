drop table if exists users;
drop table if exists roles;
drop table if exists user_role;

CREATE TABLE groups (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE users (
    username VARCHAR(255) PRIMARY KEY,
    group_id bigint,
    name VARCHAR(255),
    password VARCHAR(255),
    phone_number VARCHAR(255),
    email VARCHAR(255),
    confirm_yn BOOLEAN,
    renew_password BOOLEAN,
    created_at DATETIME NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    modified_at DATETIME,
    modified_by VARCHAR(100) NOT NULL,

    foreign key (group_id) references groups(id)
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

CREATE TABLE revision (
    rev BIGINT AUTO_INCREMENT PRIMARY KEY,
    timestamp BIGINT,
    modified_by VARCHAR(255),
    modified_at datetime,
    authorities VARCHAR(255),
    group_name VARCHAR(255)
);

CREATE TABLE users_aud (
    username VARCHAR(255) NOT NULL,
    rev bigint NOT NULL,
    revtype TINYINT,

    name VARCHAR(255),
    name_mod BOOLEAN DEFAULT FALSE,
    password VARCHAR(255),
    password_mod BOOLEAN DEFAULT FALSE,
    phone_number VARCHAR(255),
    phone_number_mod BOOLEAN DEFAULT FALSE,
    email VARCHAR(255),
    email_mod BOOLEAN DEFAULT FALSE,
    confirm_yn BOOLEAN,
    confirm_yn_mod BOOLEAN DEFAULT FALSE,
    renew_password BOOLEAN,
    renew_password_mod BOOLEAN DEFAULT FALSE,
    group_id bigint,
    group_mod BOOLEAN DEFAULT FALSE,

    CONSTRAINT user_aud_pk PRIMARY KEY (username, rev),
    FOREIGN KEY (rev) REFERENCES revision (rev)
);

CREATE TABLE user_role_aud (
    id BIGINT not null,
    rev bigint not null,
    revtype tinyint,

    role_id bigint not null,
    role_mod boolean default false,

    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES revision (rev)
);
