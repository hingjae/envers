package com.example.envers.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    USER("사용자"), ADMIN("관리자");

    private final String description;
}
