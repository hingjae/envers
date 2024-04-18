package com.example.envers.envers;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.time.LocalDateTime;

/**
 * Revision Entity를 직접 등록하고
 * @RevisionEntity, @RevisionNumber,@RevisionTimestamp 애노테이션으로 커스터마이징
 */
@Entity
@RevisionEntity(CustomRevisionListener.class)
@Table(name = "revision")
public class Revision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private Long rev;

    @RevisionTimestamp
    private Long timestamp;

    @Setter
    private String modifiedBy;

    @Setter
    private LocalDateTime modifiedAt;
}
