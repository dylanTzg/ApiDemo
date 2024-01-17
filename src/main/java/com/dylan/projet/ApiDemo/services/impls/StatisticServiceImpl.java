package com.dylan.projet.ApiDemo.services.impls;

import com.dylan.projet.ApiDemo.enums.TransactionType;
import com.dylan.projet.ApiDemo.repositories.TransactionRepository;
import com.dylan.projet.ApiDemo.services.interfaces.StatisticService;

import com.dylan.projet.ApiDemo.utils.TransactionSumDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final TransactionRepository repository;

    @Override
    public List<TransactionSumDetails> findSumTransactionByDay(LocalDate startDate, LocalDate endDate, Integer userId) {
          LocalDateTime start = LocalDateTime.of(startDate, LocalTime.of(0, 0, 0));
            LocalDateTime end = LocalDateTime.of(endDate, LocalTime.of(23, 59, 59));
        return repository.findSumTransactionByDay(start,  end, userId);
    }

    @Override
    public BigDecimal getAccountBalance(Integer userId) {
        return repository.findAccountBalance(userId);
    }

    @Override
    public BigDecimal highestTransfert(Integer userId) {
        return repository.findHighestAmountByTransactionType(userId, TransactionType.TRANSFERT);
    }

    @Override
    public BigDecimal highestDeposit(Integer userId) {
        return repository.findHighestAmountByTransactionType(userId, TransactionType.DEPOSIT);
    }
}
