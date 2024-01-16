package com.dylan.projet.ApiDemo.repositories;

import com.dylan.projet.ApiDemo.entities.TransactionEntity;
import com.dylan.projet.ApiDemo.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findAllByUserId(Integer userId);

    @Query("SELECT SUM(t.amount) FROM TransactionEntity t WHERE t.user.id = :userId")
    BigDecimal findAccountBalance(@Param("userId")Integer userId);
    @Query("SELECT max(abs(t.amount)) as amount FROM TransactionEntity t WHERE t.user.id = :userId AND t.type = :transactionType")
    BigDecimal findHighestAmountByTransactionType(Integer userId, TransactionType transactionType);

    Map<LocalDate, BigDecimal> findSumTransactionByDay(LocalDate startDate, LocalDate endDate, Integer userId);
}