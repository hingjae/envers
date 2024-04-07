package com.example.envers.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Audited
@Entity
public class User {

    @Id
    private String username;

    @Column
    @Audited(withModifiedFlag = true)
    private String name;

    @Column
    @Audited(withModifiedFlag = true)
    private String password; // 비밀번호 변경, 초기화 구분해야함.

    @Column
    @Audited(withModifiedFlag = true)
    private String phoneNumber;

    @Column
    private String email;

    @Column
    private Boolean confirmYn = false;

    @Column
    private boolean renewPassword = false;

    @NotAudited
    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<>();

    @Builder
    public User(String username, String name, String password, String phoneNumber, String email, Boolean confirmYn, boolean renewPassword, List<UserRole> userRoles) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.confirmYn = confirmYn;
        this.renewPassword = renewPassword;
        this.userRoles = userRoles;
    }
}