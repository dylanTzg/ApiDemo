package com.dylan.projet.ApiDemo.services.interfaces;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface StatisticService {

    Map<LocalDate, BigDecimal> findSumTransactionByDay(LocalDate startDate, LocalDate endDate, Integer userId);

    BigDecimal getAccountBalance(Integer userId);

    BigDecimal highestTransfert(Integer userId);

    BigDecimal highestDeposit(Integer userId);



}
