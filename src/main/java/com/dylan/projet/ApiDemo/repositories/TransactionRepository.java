package com.dylan.projet.ApiDemo.repositories;

import com.dylan.projet.ApiDemo.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findAllByUserId(Integer userId);

}