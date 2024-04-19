package com.example.envers.user.controller.response;

import com.example.envers.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class UserResponseList {
    private List<UserResponse> items;

    public static UserResponseList from(List<User> users) {
        return UserResponseList.builder()
                .items(users.stream()
                        .map(UserResponse::from)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
