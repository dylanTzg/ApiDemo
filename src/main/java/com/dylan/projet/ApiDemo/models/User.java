package com.dylan.projet.ApiDemo.models;

import com.dylan.projet.ApiDemo.entities.AddressEntity;
import com.dylan.projet.ApiDemo.entities.UserEntity;
import com.dylan.projet.ApiDemo.models.parent.ParentModel;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User extends ParentModel {

    @NotNull(message = "Last name cannot be null")
    @NotEmpty(message = "Last name cannot be empty")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First name cannot be empty")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
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
                .creationDate(entity.getCreationDate())
                .lastUpdate(entity.getLastUpdate())
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
                .creationDate(user.getCreationDate())
                .lastUpdate(user.getLastUpdate())
                .build();
    }
}
