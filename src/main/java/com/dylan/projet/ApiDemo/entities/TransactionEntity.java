package com.dylan.projet.ApiDemo.entities;

import com.dylan.projet.ApiDemo.entities.parent.ParentEntity;
import com.dylan.projet.ApiDemo.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Column(updatable = false)
    private LocalDate transactionDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
