package com.dylan.projet.ApiDemo.services.interfaces;

import com.dylan.projet.ApiDemo.models.Transaction;
import com.dylan.projet.ApiDemo.services.interfaces.parent.ParentService;

import java.util.List;

public interface TransactionService extends ParentService<Transaction> {

    List<Transaction> findAllByUserId(Integer userId);
}
