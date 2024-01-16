package com.dylan.projet.ApiDemo.models;

import com.dylan.projet.ApiDemo.entities.RoleEntity;
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
public class Role extends ParentModel {

    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    private User user;


    public static Role fromEntity(RoleEntity entity) {
        return Role.builder()
                .id(entity.getId())
                .name(entity.getName())
                .user(User.fromEntity(entity.getUser()))
                .build();
    }

    public static RoleEntity toEntity(Role role) {
        return RoleEntity.builder()
                .name(role.getName())
                .user(User.toEntity(role.getUser()))
                .build();
    }
}
