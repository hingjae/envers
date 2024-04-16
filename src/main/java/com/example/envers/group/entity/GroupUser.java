package com.example.envers.group.entity;

import com.example.envers.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class GroupUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupUserSn;

    @ManyToOne
    @JoinColumn(name = "group_sn")
    @Audited(withModifiedFlag = true, targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    @Builder
    public GroupUser(Long groupUserSn, Group group, User user) {
        this.groupUserSn = groupUserSn;
        this.group = group;
        this.user = user;
    }

    public void update(Group group, User user) {
        this.group = group;
        this.user = user;
    }
}
