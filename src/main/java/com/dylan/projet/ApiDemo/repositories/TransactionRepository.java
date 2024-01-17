package com.dylan.projet.ApiDemo.repositories;

import com.dylan.projet.ApiDemo.entities.TransactionEntity;
import com.dylan.projet.ApiDemo.enums.TransactionType;
import com.dylan.projet.ApiDemo.utils.TransactionSumDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findAllByUserId(Integer userId);

    @Query("select sum(t.amount) from TransactionEntity t where t.user.id = :userId")
    BigDecimal findAccountBalance(@Param("userId") Integer userId);

    @Query("select max(abs(t.amount)) as amount from TransactionEntity t where t.user.id = :userId AND t.type = :transactionType")
    BigDecimal findHighestAmountByTransactionType(Integer userId, TransactionType transactionType);

    @Query("select t.transactionDate as transactionDate, sum(t.amount) as amount from TransactionEntity t where t.creationDate between :startDate and :endDate and t.user.id = :userId group by t.transactionDate")
    List<TransactionSumDetails> findSumTransactionByDay(LocalDateTime startDate, LocalDateTime endDate, Integer userId);
}