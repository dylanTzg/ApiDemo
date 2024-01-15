package com.dylan.projet.ApiDemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Contact extends AbstractEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String lastName;

    private String firstName;

    private String email;

    private String password;

    private String iban;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
