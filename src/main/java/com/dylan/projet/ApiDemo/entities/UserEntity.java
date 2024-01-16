package com.dylan.projet.ApiDemo.entities;

import com.dylan.projet.ApiDemo.entities.parent.ParentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_user")
public class UserEntity extends ParentEntity {


    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,
    columnDefinition = "boolean default false")
    private Boolean active;

    @OneToMany(mappedBy = "user")
    private List<TransactionEntity> transactions;

    @OneToOne(mappedBy = "user")
    private AccountEntity account;

    @OneToMany(mappedBy = "user")
    private List<ContactEntity> contacts;

    @OneToOne(mappedBy = "user")
    private AddressEntity address;

    @OneToOne(mappedBy = "user")
    private RoleEntity role;
}
