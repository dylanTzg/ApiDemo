package com.dylan.projet.ApiDemo.models.parent;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
@Data
public class ParentModel {

    private Integer id;

    private LocalDate creationDate;

    private LocalDate lastUpdate;

}
