package com.example.envers.user.envers;

import jakarta.persistence.*;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

/**
 * Revision Entity를 직접 등록하고
 * @RevisionEntity, @RevisionNumber,@RevisionTimestamp 애노테이션으로 커스터마이징
 */
@Entity
@RevisionEntity(UserRevisionEntityListener.class)
@Table(name = "user_revision")
public class UserRevision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    @Column(name = "id")
    private Long id;

    @RevisionTimestamp
    @Column(name = "timestamp")
    private Long timestamp;

}
