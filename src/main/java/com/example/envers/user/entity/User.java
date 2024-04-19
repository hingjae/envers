package com.example.envers.user.entity;

import com.example.envers.common.audit.AuditingFields;
import com.example.envers.group.entity.Group;
import com.example.envers.role.entity.Role;
import com.example.envers.role.entity.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends AuditingFields {

    @Id
    private String username;

    @Setter
    @Audited(withModifiedFlag = true, targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Setter
    @Column
    @Audited(withModifiedFlag = true)
    private String password; // 비밀번호 변경, 초기화 구분해야함.

    @Setter
    @Column
    @Audited(withModifiedFlag = true)
    private String name;

    @Setter
    @Column
    @Audited(withModifiedFlag = true)
    private String phoneNumber;

    @Setter
    @Column
    @Audited(withModifiedFlag = true)
    private String email;

    @Setter
    @Column
    @Audited(withModifiedFlag = true)
    private Boolean confirmYn = false;

    @Setter
    @Column
    @Audited(withModifiedFlag = true)
    private Boolean renewPassword = false;

    @Setter
    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<>();

    @Builder
    public User(String username, Group group, String password, String name, String phoneNumber, String email, Boolean confirmYn, Boolean renewPassword, List<UserRole> userRoles, String createdBy, LocalDateTime createdAt, String modifiedBy, LocalDateTime modifiedAt) {
        this.username = username;
        this.group = group;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.confirmYn = confirmYn;
        this.renewPassword = renewPassword;
        this.userRoles = userRoles;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public String getGroupName() {
        return group.getName();
    }

    public Boolean isGroupUser() {
        return userRoles.stream()
                .map(UserRole::getRole)
                .map(Role::getRoleType)
                .anyMatch(roleType -> roleType == RoleType.GROUP_USER);
    }
}
