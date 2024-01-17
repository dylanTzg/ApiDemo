package com.dylan.projet.ApiDemo.entities.parent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public class ParentEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(
            updatable = false,
            nullable = false
    )
    private LocalDateTime creationDate;

    @LastModifiedDate
    private LocalDateTime lastUpdate;

}
