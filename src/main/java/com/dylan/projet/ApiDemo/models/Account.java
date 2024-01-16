package com.dylan.projet.ApiDemo.models;

import com.dylan.projet.ApiDemo.entities.AccountEntity;
import com.dylan.projet.ApiDemo.models.parent.ParentModel;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Account extends ParentModel {

    @NotNull
    @NotEmpty
    @NotBlank
    private String iban;

    private User user;


    public static Account fromEntity(AccountEntity entity) {
        return Account.builder()
                .id(entity.getId())
                .iban(entity.getIban())
                .user(User.fromEntity(entity.getUser()))
                .build();
    }

    public static AccountEntity toEntity(Account account) {
        return com.dylan.projet.ApiDemo.entities.AccountEntity.builder()
                .iban(account.getIban())
                .user(User.toEntity(account.getUser()))
                .build();
    }
}
