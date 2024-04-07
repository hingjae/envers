package com.example.envers.user.controller.form;

import com.example.envers.user.entity.UserRole;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class AddUserForm {
    private final String username;

    private final String password;

    private final String name;

    private final String phoneNumber;

    private final String email;
}
