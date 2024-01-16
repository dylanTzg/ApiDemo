package com.dylan.projet.ApiDemo.repositories;

import com.dylan.projet.ApiDemo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndBirthDate(String firstName, String lastName, LocalDate birthDate);

    UserEntity findByEmail(String email);


}
