package com.dylan.projet.ApiDemo.repositories;

import com.dylan.projet.ApiDemo.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}