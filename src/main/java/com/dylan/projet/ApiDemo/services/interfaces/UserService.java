package com.dylan.projet.ApiDemo.services.interfaces;

import com.dylan.projet.ApiDemo.models.Address;
import com.dylan.projet.ApiDemo.models.AuthRequest;
import com.dylan.projet.ApiDemo.models.AuthResponse;
import com.dylan.projet.ApiDemo.models.User;
import com.dylan.projet.ApiDemo.services.interfaces.parent.ParentService;

import java.time.LocalDate;
import java.util.List;

public interface UserService extends ParentService<User> {
    AuthResponse register(User user);

    Integer validateAccount(Integer id);

   Integer invalidateAccount(Integer id);

   User findIdByEmail(String email);

   List<User> findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndBirthDate(String firstName, String lastName, LocalDate birthDate);

   Address findAddressByUsderId(User user);

    AuthResponse login(AuthRequest authRequest);
}
