package com.example.envers.user.service;

import com.example.envers.user.controller.form.AddUserForm;
import com.example.envers.user.entity.Role;
import com.example.envers.user.entity.RoleType;
import com.example.envers.user.entity.User;
import com.example.envers.user.entity.UserRole;
import com.example.envers.user.repository.RoleRepository;
import com.example.envers.user.repository.UserRepository;
import com.example.envers.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    public void save(AddUserForm addUserForm) {
        Role role = roleRepository.findByRoleType(RoleType.USER);
        User user = userRepository.save(createUser(addUserForm));
        UserRole userRole = userRoleRepository.save(createUserRole(user, role));
    }

    private User createUser(AddUserForm form) {
        return User.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .name(form.getName())
                .phoneNumber(form.getPhoneNumber())
                .email(form.getEmail())
                .build();
    }

    private UserRole createUserRole(User user, Role role) {
        return UserRole.builder()
                .user(user)
                .role(role)
                .build();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
