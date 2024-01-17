package com.dylan.projet.ApiDemo.models;

import com.dylan.projet.ApiDemo.entities.AccountEntity;
import com.dylan.projet.ApiDemo.entities.UserEntity;
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


    private String iban;

    private User user;


    public static Account fromEntity(AccountEntity entity) {
        return Account.builder()
                .id(entity.getId())
                .iban(entity.getIban())
                .user(User.builder().id(entity.getUser().getId()).build())
                .creationDate(entity.getCreationDate())
                .creationDate(entity.getCreationDate())
                .lastUpdate(entity.getLastUpdate())
                .build();
    }

    public static AccountEntity toEntity(Account account) {
        return com.dylan.projet.ApiDemo.entities.AccountEntity.builder()
                .iban(account.getIban())
                .user(UserEntity.builder().id(account.getUser().getId()).build())
                .build();
    }
}
