package com.dylan.projet.ApiDemo.entities;

import com.dylan.projet.ApiDemo.entities.parent.ParentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_user")
public class UserEntity extends ParentEntity implements UserDetails {

    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,
            columnDefinition = "boolean default false")
    private Boolean active;

    @OneToMany(mappedBy = "user")
    private List<TransactionEntity> transactions;

    @OneToOne(mappedBy = "user")
    private AccountEntity account;

    @OneToMany(mappedBy = "user")
    private List<ContactEntity> contacts;

    @OneToOne(mappedBy = "user")
    private AddressEntity address;


    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
