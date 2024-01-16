package com.dylan.projet.ApiDemo.models;

import com.dylan.projet.ApiDemo.entities.ContactEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {


    private Integer id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String lastName;

    @NotNull
    @NotEmpty
    @NotBlank
    private String firstName;


    private String iban;

    private User user;


    public static Contact fromEntity(ContactEntity entity) {
        return Contact.builder()
                .id(entity.getId())
                .lastName(entity.getLastName())
                .firstName(entity.getFirstName())
                .iban(entity.getIban())
                .user(User.fromEntity(entity.getUser()))
                .build();
    }

    public static ContactEntity toEntity(Contact contact) {
        return ContactEntity.builder()
                .lastName(contact.getLastName())
                .firstName(contact.getFirstName())
                .iban(contact.getIban())
                .user(User.toEntity(contact.getUser()))
                .build();
    }
}
