package com.example.envers.group.repository;

import com.example.envers.group.entity.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {

    @Query("select gu " +
            "from GroupUser gu " +
            "where gu.user.username = :username")
    Optional<GroupUser> findByUsername(@Param("username") String username);
}
