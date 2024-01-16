package com.dylan.projet.ApiDemo.models;

import com.dylan.projet.ApiDemo.entities.AccountEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    private Integer id;

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
