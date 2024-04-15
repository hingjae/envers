package com.example.envers.role.repository;

import com.example.envers.role.entity.Role;
import com.example.envers.role.entity.RoleType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Cacheable("roles")
    Role findByRoleType(RoleType roleType);
}
