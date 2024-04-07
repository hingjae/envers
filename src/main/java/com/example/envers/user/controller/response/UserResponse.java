package com.example.envers.user.controller.response;

import com.example.envers.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    private final String username;

    private final String password;

    private final String name;

    private final String phoneNumber;

    private final String email;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .build();
    }

}
