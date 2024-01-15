package com.dylan.projet.ApiDemo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_user")
public class User extends AbstractEntity {

    private String lastName;

    private String firstName;

    private String email;

    private String password;

    private Boolean active;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    @OneToOne
    private Account account;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

    @OneToOne
    private Address address;

    @OneToOne
    private Role role;
}
