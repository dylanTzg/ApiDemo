package com.dylan.projet.ApiDemo.models;

import com.dylan.projet.ApiDemo.entities.TransactionEntity;
import com.dylan.projet.ApiDemo.entities.UserEntity;
import com.dylan.projet.ApiDemo.enums.TransactionType;
import com.dylan.projet.ApiDemo.models.parent.ParentModel;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Transaction extends ParentModel {

    @Positive
    @Max(1000000)
    private BigDecimal amount;

    private TransactionType type;

    private String destinationIban;

    private LocalDate transactionDate;

    private Integer userId;

    public static Transaction fromEntity(TransactionEntity entity) {
        return Transaction.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .type(entity.getType())
                .destinationIban(entity.getDestinationIban())
                .userId(entity.getUser().getId())
                .creationDate(entity.getCreationDate())
                .lastUpdate(entity.getLastUpdate())
                .transactionDate(entity.getTransactionDate())
                .build();
    }

    public static TransactionEntity toEntity(Transaction transaction) {
        return TransactionEntity.builder()
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .id(transaction.getId())
                .user(UserEntity.builder().id(transaction.getUserId()).build())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }
}
