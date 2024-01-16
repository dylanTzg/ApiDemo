package com.dylan.projet.ApiDemo.repositories;

import com.dylan.projet.ApiDemo.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
}