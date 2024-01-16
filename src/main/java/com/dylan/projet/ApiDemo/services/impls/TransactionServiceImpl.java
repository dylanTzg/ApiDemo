package com.dylan.projet.ApiDemo.services.impls;

import com.dylan.projet.ApiDemo.entities.TransactionEntity;
import com.dylan.projet.ApiDemo.enums.TransactionType;
import com.dylan.projet.ApiDemo.models.Transaction;
import com.dylan.projet.ApiDemo.repositories.TransactionRepository;
import com.dylan.projet.ApiDemo.services.interfaces.TransactionService;
import com.dylan.projet.ApiDemo.utils.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    private final ObjectValidator objectValidator;

    @Override
    public Integer save(Transaction model) {
        objectValidator.validate(model);
        TransactionEntity transactionEntity = Transaction.toEntity(model);
        BigDecimal amount = transactionEntity.getAmount();
        amount = amount.multiply(BigDecimal.valueOf(transactionTypeConvert(transactionEntity.getType())));
        transactionEntity.setAmount(amount);
        return repository.save(transactionEntity).getId();
    }

    @Override
    public List<Transaction> findAll() {
        return repository.findAll()
                .stream()
                .map(Transaction::fromEntity)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Transaction findById(Integer id) {
        return repository.findById(id)
                .map(Transaction::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
    }

    @Override
    public void deleteById(Integer id) {
            repository.deleteById(id);
    }


    private int transactionTypeConvert(TransactionType transactionType) {
        switch (transactionType) {
            case DEPOSIT:
                return 1;
            case TRANSFERT:
                return -1;
        }
        return 0;
    }

    @Override
    public List<Transaction> findAllByUserId(Integer userId) {
      return repository.findAllByUserId(userId)
        .stream()
        .map(Transaction::fromEntity)
        .collect(java.util.stream.Collectors.toList());
    }
}
