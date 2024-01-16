package com.dylan.projet.ApiDemo.models;

import com.dylan.projet.ApiDemo.entities.AddressEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    private Integer id;

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

    private User user;

    public static Address fromEntity(AddressEntity entity) {
        return Address.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .houseNumber(entity.getHouseNumber())
                .zipCode(entity.getZipCode())
                .city(entity.getCity())
                .country(entity.getCountry())
                .user(User.fromEntity(entity.getUser()))
                .build();
    }

    public static AddressEntity toEntity(Address address) {
        return com.dylan.projet.ApiDemo.entities.AddressEntity.builder()
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .user(User.toEntity(address.getUser()))
                .build();
    }

}
