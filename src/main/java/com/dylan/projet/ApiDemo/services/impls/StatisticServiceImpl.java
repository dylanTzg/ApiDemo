package com.dylan.projet.ApiDemo.services.impls;

import com.dylan.projet.ApiDemo.enums.TransactionType;
import com.dylan.projet.ApiDemo.repositories.TransactionRepository;
import com.dylan.projet.ApiDemo.services.interfaces.StatisticService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final TransactionRepository repository;

    @Override
    public Map<LocalDate, BigDecimal> findSumTransactionByDay(LocalDate startDate, LocalDate endDate, Integer userId) {
        return repository.findSumTransactionByDay(startDate, endDate, userId);
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
