package com.dylan.projet.ApiDemo.repositories;

import com.dylan.projet.ApiDemo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    UserEntity findByEmail(String email);

    List<UserEntity> findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndBirthDate(String firstName, String lastName, LocalDate birthDate);


}
