package com.dylan.projet.ApiDemo.models;

import com.dylan.projet.ApiDemo.entities.TransactionEntity;
import com.dylan.projet.ApiDemo.enums.TransactionType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @NotNull
    private Integer id;

    @Positive
    @Max(1000000)
    private BigDecimal amount;

    private TransactionType type;

    @NotNull
    @NotBlank
    @NotEmpty
    private String destinationIban;

    @NotNull
    private User user;

    public static Transaction fromEntity(TransactionEntity entity) {
        return Transaction.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .type(entity.getType())
                .destinationIban(entity.getDestinationIban())
                .user(User.fromEntity(entity.getUser()))
                .build();
    }

    public static TransactionEntity toEntity(Transaction transaction) {
        return TransactionEntity.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .user(User.toEntity(transaction.getUser()))
                .build();
    }
}
