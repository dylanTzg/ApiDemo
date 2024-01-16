package com.dylan.projet.ApiDemo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "contact")
public class ContactEntity extends ParentEntity {


    private String lastName;

    private String firstName;

    private String iban;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
