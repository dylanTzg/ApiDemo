package com.dylan.projet.ApiDemo.services.impls;

import com.dylan.projet.ApiDemo.entities.UserEntity;
import com.dylan.projet.ApiDemo.models.Account;
import com.dylan.projet.ApiDemo.models.User;
import com.dylan.projet.ApiDemo.repositories.UserRepository;
import com.dylan.projet.ApiDemo.services.interfaces.AccountService;
import com.dylan.projet.ApiDemo.services.interfaces.UserService;
import com.dylan.projet.ApiDemo.utils.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final AccountService accountService;

    private final ObjectValidator<User> validator;

    @Override
    public Integer save(User model) {
        validator.validate(model);
        UserEntity userEntity = User.toEntity(model);
        repository.save(userEntity);
        return repository.save(userEntity).getId();
    }

    @Override
    public List<User> findAll() {
        return repository.findAll()
                .stream().
                map(User::fromEntity).
                collect(Collectors.toList());
    }

    @Override
    public User findById(Integer id) {
        return repository.findById(id)
                .map(User::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Integer validateAccount(Integer id) {
        UserEntity userEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userEntity.setActive(true);
        Account account = Account.builder()
                .user(User.fromEntity(userEntity))
                .build();
        accountService.save(account);
        return null;
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        UserEntity userEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userEntity.setActive(false);
        return repository.save(userEntity).getId();
    }
}
