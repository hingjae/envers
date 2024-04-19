package com.example.envers.user.service;

import com.example.envers.group.entity.Group;
import com.example.envers.group.repository.GroupRepository;
import com.example.envers.role.entity.Role;
import com.example.envers.role.entity.RoleType;
import com.example.envers.role.repository.RoleRepository;
import com.example.envers.user.controller.form.AddUserForm;
import com.example.envers.user.controller.form.ModifyUserForm;
import com.example.envers.user.entity.User;
import com.example.envers.user.entity.UserRole;
import com.example.envers.user.repository.UserRepository;
import com.example.envers.user.repository.UserRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final RoleRepository roleRepository;

    private final GroupRepository groupRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User save(AddUserForm addUserForm) {
        Role role = roleRepository.findByRoleType(RoleType.GROUP_USER);
        Group group = groupRepository.findByName(addUserForm.getGroupName())
                .orElseThrow(EntityNotFoundException::new);

        User user = userRepository.save(createUser(addUserForm, group));
        userRoleRepository.save(createUserRole(user, role));

        return user;
    }

    private User createUser(AddUserForm form, Group group) {
        return User.builder()
                .username(form.getUsername())
                .group(group)
                .password(passwordEncoder.encode(form.getPassword()))
                .name(form.getName())
                .phoneNumber(form.getPhoneNumber())
                .email(form.getEmail())
                .confirmYn(false)
                .renewPassword(false)
                .createdBy(form.getUsername())
                .modifiedBy(form.getUsername())
                .build();
    }

    private UserRole createUserRole(User user, Role role) {
        return UserRole.builder()
                .user(user)
                .role(role)
                .build();
    }

    public List<User> findAllWithGroup() {
        return userRepository.findAllWithGroupAndRoles();
    }

    public User findById(String username) {
        return userRepository.findByUsernameWithGroup(username)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void modify(String username, ModifyUserForm form) {
        User user = findById(username);
        Group group = groupRepository.findByName(form.getGroupName())
                .orElseThrow(EntityNotFoundException::new);

        user.setName(form.getName());
        user.setPhoneNumber(form.getPhoneNumber());
        user.setEmail(form.getEmail());
        user.setGroup(group);
    }

    @Transactional
    public void modifyRole(String username, RoleType roleType) {
        UserRole userRole = userRoleRepository.findByUser_UsernameAndRole_RoleType(username, roleType)
                .orElseThrow(EntityNotFoundException::new);

        if (userRole.isGroupUser()) {
            Role groupAdmin = roleRepository.findByRoleType(RoleType.GROUP_ADMIN);
            userRole.setRole(groupAdmin);
        } else {
            Role groupAdmin = roleRepository.findByRoleType(RoleType.GROUP_USER);
            userRole.setRole(groupAdmin);
        }
    }
}
