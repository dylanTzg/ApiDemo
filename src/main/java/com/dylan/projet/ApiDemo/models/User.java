package com.dylan.projet.ApiDemo.models;

import com.dylan.projet.ApiDemo.entities.UserEntity;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {


    private Integer id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String lastName;

    @NotNull
    @NotEmpty
    @NotBlank
    private String firstName;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 8)
    private String password;

    private Boolean active;


    public static User fromEntity(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .lastName(entity.getLastName())
                .firstName(entity.getFirstName())
                .birthDate(entity.getBirthDate())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .active(entity.getActive())
                .build();
    }

   public static UserEntity toEntity(User user) {
            return UserEntity.builder()
                    .lastName(user.getLastName())
                    .firstName(user.getFirstName())
                    .birthDate(user.getBirthDate())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .active(user.getActive())
                    .build();
        }
}
