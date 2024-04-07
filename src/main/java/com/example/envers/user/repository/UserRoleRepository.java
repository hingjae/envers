package com.example.envers.user.repository;

import com.example.envers.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
