package com.dylan.projet.ApiDemo.services.impls;

import com.dylan.projet.ApiDemo.entities.AddressEntity;
import com.dylan.projet.ApiDemo.entities.UserEntity;
import com.dylan.projet.ApiDemo.models.Account;
import com.dylan.projet.ApiDemo.models.Address;
import com.dylan.projet.ApiDemo.models.User;
import com.dylan.projet.ApiDemo.repositories.AddressRepository;
import com.dylan.projet.ApiDemo.repositories.UserRepository;
import com.dylan.projet.ApiDemo.services.interfaces.AccountService;
import com.dylan.projet.ApiDemo.services.interfaces.UserService;
import com.dylan.projet.ApiDemo.utils.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final AccountService accountService;

    private final AddressRepository addressRepository;

    private final ObjectValidator<User> validator;

    @Override
    public Integer save(User model) {
        model.setActive(false);
        validator.validate(model);
        UserEntity userEntity = User.toEntity(model);
        repository.save(userEntity);
        return repository.save(userEntity).getId();
    }

    @Override
    public List<User> findAll() {
        List<User> userList = repository.findAll()
                .stream().
                map(User::fromEntity).
                toList();
        return userList;
    }

    @Override
    public User findById(Integer id) {
        User user = repository.findById(id)
                .map(User::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Integer validateAccount(Integer id) {
        UserEntity userEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Account account = Account.builder()
                .user(User.fromEntity(userEntity))
                .build();
        if (userEntity.getAccount() == null) {
            accountService.save(account);
        }
        userEntity.setActive(true);
        repository.save(userEntity);
        return userEntity.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        UserEntity userEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userEntity.setActive(false);
        return repository.save(userEntity).getId();
    }

    @Override
    public User findIdByEmail(String email) {
        UserEntity userEntity = repository.findByEmail(email);
        if (userEntity == null) {
            throw new EntityNotFoundException("User not found");
        }
        User user = User.fromEntity(userEntity);
        return user;
    }

    @Override
    public List<User> findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndBirthDate(String firstName, String
            lastName, LocalDate birthDate) {
        List<UserEntity> userEntity = repository.findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndBirthDate(firstName, lastName, birthDate);
        if (userEntity == null) {
            throw new EntityNotFoundException("User not found");
        }
        List<User> users = userEntity.stream()
                .map(User::fromEntity)
                .collect(Collectors.toList());
        return users;
    }

    @Override
    public Address findAddressByUsderId(User user) {
        UserEntity userEntity = repository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        AddressEntity addressEntity = addressRepository.findByUser(userEntity);
        if (addressEntity == null) {
            return null;
        }
        return Address.fromEntity(addressEntity);
    }

}
