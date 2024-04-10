package com.example.envers.user.repository;

import com.example.envers.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>, RevisionRepository<User, String, Long> {
    @Query("select u " +
            "from User u " +
            "join fetch u.userRoles ur " +
            "join fetch ur.role r " +
            "where u.username = :username")
    Optional<User> findUserWithRolesByUsername(@Param("username") String username);
}
