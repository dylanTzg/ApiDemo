package com.dylan.projet.ApiDemo.models;

import com.dylan.projet.ApiDemo.entities.TransactionEntity;
import com.dylan.projet.ApiDemo.enums.TransactionType;
import com.dylan.projet.ApiDemo.models.parent.ParentModel;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
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
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .user(User.toEntity(transaction.getUser()))
                .build();
    }
}
