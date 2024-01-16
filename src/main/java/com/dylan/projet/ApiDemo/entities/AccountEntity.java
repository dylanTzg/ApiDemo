package com.dylan.projet.ApiDemo.entities;

import com.dylan.projet.ApiDemo.entities.parent.ParentEntity;
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
@Table(name = "account")
public class AccountEntity extends ParentEntity {

    private String iban;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
