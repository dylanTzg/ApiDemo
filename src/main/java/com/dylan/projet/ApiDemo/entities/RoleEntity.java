package com.dylan.projet.ApiDemo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class RoleEntity extends ParentEntity {


    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
