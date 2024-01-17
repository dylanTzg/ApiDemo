package com.dylan.projet.ApiDemo.models;

import com.dylan.projet.ApiDemo.entities.AddressEntity;
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
public class Address extends ParentModel {

    @NotNull
    @NotEmpty
    @NotBlank
    private String street;

    @NotNull
    private Integer houseNumber;

    @NotNull
    private Integer zipCode;

    @NotNull
    @NotEmpty
    @NotBlank
    private String city;

    @NotNull
    @NotEmpty
    @NotBlank
    private String country;

    private Integer userId;

    public static Address fromEntity(AddressEntity entity) {
        return Address.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .houseNumber(entity.getHouseNumber())
                .zipCode(entity.getZipCode())
                .city(entity.getCity())
                .country(entity.getCountry())
                .userId(entity.getUser().getId())
                .creationDate(entity.getCreationDate())
                .lastUpdate(entity.getLastUpdate())
                .build();
    }

    public static AddressEntity toEntity(Address address) {
        return com.dylan.projet.ApiDemo.entities.AddressEntity.builder()
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .user(UserEntity.builder().id(address.getUserId()).build())
                .build();
    }

}
