package com.dylan.projet.ApiDemo.repositories;

import com.dylan.projet.ApiDemo.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}