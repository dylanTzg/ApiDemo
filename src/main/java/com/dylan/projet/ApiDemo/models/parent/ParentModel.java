package com.dylan.projet.ApiDemo.models.parent;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
@Data
public class ParentModel {

    private Integer id;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdate;

}
