package com.dylan.projet.ApiDemo.entities;

import com.dylan.projet.ApiDemo.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
public class TransactionEntity extends ParentEntity {


    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String destinationIban;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
