package com.example.envers.user.repository;

import com.example.envers.role.entity.RoleType;
import com.example.envers.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByUser_UsernameAndRole_RoleType(String username, RoleType roleType);
}
