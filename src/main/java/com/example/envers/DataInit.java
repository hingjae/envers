package com.example.envers;

import com.example.envers.user.entity.Role;
import com.example.envers.user.entity.RoleType;
import com.example.envers.user.entity.User;
import com.example.envers.user.entity.UserRole;
import com.example.envers.user.repository.RoleRepository;
import com.example.envers.user.repository.UserRepository;
import com.example.envers.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataInit {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Role role = roleRepository.findByRoleType(RoleType.USER);

        userRoleRepository.save(UserRole.builder()
                .user(
                        userRepository.save(
                                User.builder()
                                        .username("username1")
                                        .password("password1")
                                        .name("name1")
                                        .email("email1")
                                        .phoneNumber("01012341234")
                                        .confirmYn(false)
                                        .renewPassword(false)
                                        .build()
                        ))
                .role(role)
                .build());

        userRoleRepository.save(UserRole.builder()
                .user(
                        userRepository.save(
                                User.builder()
                                        .username("username2")
                                        .password("password2")
                                        .name("name2")
                                        .email("email2")
                                        .phoneNumber("01012341234")
                                        .confirmYn(false)
                                        .renewPassword(false)
                                        .build()
                        ))
                .role(role)
                .build());

        userRoleRepository.save(UserRole.builder()
                .user(
                        userRepository.save(
                                User.builder()
                                        .username("username3")
                                        .password("password3")
                                        .name("name3")
                                        .email("email3")
                                        .phoneNumber("01012341234")
                                        .confirmYn(false)
                                        .renewPassword(false)
                                        .build()
                        ))
                .role(role)
                .build());

        userRoleRepository.save(UserRole.builder()
                .user(
                        userRepository.save(
                                User.builder()
                                        .username("username4")
                                        .password("password4")
                                        .name("name4")
                                        .email("email4")
                                        .phoneNumber("01012341234")
                                        .confirmYn(false)
                                        .renewPassword(false)
                                        .build()
                        ))
                .role(role)
                .build());
    }
}
