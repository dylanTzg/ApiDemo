package com.dylan.projet.ApiDemo.services.impls;

import com.dylan.projet.ApiDemo.entities.AccountEntity;
import com.dylan.projet.ApiDemo.entities.UserEntity;
import com.dylan.projet.ApiDemo.exceptions.OperationNotPermittedException;
import com.dylan.projet.ApiDemo.models.Account;
import com.dylan.projet.ApiDemo.repositories.AccountRepository;
import com.dylan.projet.ApiDemo.repositories.UserRepository;
import com.dylan.projet.ApiDemo.services.interfaces.AccountService;
import com.dylan.projet.ApiDemo.utils.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    private final UserRepository userRepository;

    private final ObjectValidator<Account> validator;

    @Override
    @Transactional
    public Integer save(Account model) {
        AccountEntity accountEntity = Account.toEntity(model);
        boolean userHasAccount = repository.findByUserId(model.getUser().getId()).isPresent();
       Optional<UserEntity> userEntity = userRepository.findById(model.getUser().getId());
       System.out.println(userEntity.get().getActive());
        if (userHasAccount && userEntity.get().getActive()) {
            throw new OperationNotPermittedException(
                    "User already has an account"
                    , "create an account",
                    "acount service",
                    "acount creation");
        }
        if (model.getIban() == null) {
            accountEntity.setIban(generateIban());
        }
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

    @Override
    public Account findByUserId(Integer id) {
        return repository.findByUserId(id)
                .map(Account::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

}
