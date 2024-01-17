package com.dylan.projet.ApiDemo.services.interfaces;

import com.dylan.projet.ApiDemo.utils.TransactionSumDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StatisticService {

    List<TransactionSumDetails> findSumTransactionByDay(LocalDate startDate, LocalDate endDate, Integer userId);

    BigDecimal getAccountBalance(Integer userId);

    BigDecimal highestTransfert(Integer userId);

    BigDecimal highestDeposit(Integer userId);



}
