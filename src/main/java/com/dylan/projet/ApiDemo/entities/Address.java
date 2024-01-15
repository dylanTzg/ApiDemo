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
public class Address extends AbstractEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String street;

    private Integer houseNumber;

    private Integer zipCode;

    private String city;

    private String country;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
