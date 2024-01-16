package com.dylan.projet.ApiDemo.models;

import com.dylan.projet.ApiDemo.entities.RoleEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    private Integer id;

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
