package com.dylan.projet.ApiDemo.repositories;

import com.dylan.projet.ApiDemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByFirstName(String firstName);

    User findByLastNameAndFirstNameAndEmail(String lastName, String firstName, String email);

    User findByAccount_Iban(String iban);


}
