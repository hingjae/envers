package com.example.envers.user.repository;

import com.example.envers.user.entity.Role;
import com.example.envers.user.entity.RoleType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Cacheable("roles")
    Role findByRoleType(RoleType roleType);
}
