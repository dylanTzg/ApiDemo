package com.dylan.projet.ApiDemo.repositories;

import com.dylan.projet.ApiDemo.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {
}