package com.dylan.projet.ApiDemo.utils;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TransactionSumDetails {

    LocalDate getTransactionDate();


    BigDecimal getAmount();
}
