package com.dylan.projet.ApiDemo.services.impls;

import com.dylan.projet.ApiDemo.entities.AccountEntity;
import com.dylan.projet.ApiDemo.models.Account;
import com.dylan.projet.ApiDemo.repositories.AccountRepository;
import com.dylan.projet.ApiDemo.services.interfaces.AccountService;
import com.dylan.projet.ApiDemo.utils.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    private final ObjectValidator<Account> validator;

    @Override
    public Integer save(Account model) {
        validator.validate(model);
        if (model.getId() == null)
        {
            model.setIban(generateIban());
        }
        AccountEntity accountEntity = Account.toEntity(model);
        return repository.save(accountEntity).getId();
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll()
                .stream()
                .map(Account::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Account findById(Integer id) {
        return repository.findById(id)
                .map(Account::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    private String generateIban() {
        String iban = Iban.random(CountryCode.FR).toFormattedString();
        boolean isIbanExist = repository.findByIban(iban).isPresent();
        if (isIbanExist) {
            generateIban();
        }
        return iban;
    }
}
