package com.dylan.projet.ApiDemo.repositories;

import com.dylan.projet.ApiDemo.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}