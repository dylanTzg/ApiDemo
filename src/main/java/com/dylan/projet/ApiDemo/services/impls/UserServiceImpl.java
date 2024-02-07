package com.dylan.projet.ApiDemo.services.impls;

import com.dylan.projet.ApiDemo.config.JwtUtils;
import com.dylan.projet.ApiDemo.entities.AddressEntity;
import com.dylan.projet.ApiDemo.entities.UserEntity;
import com.dylan.projet.ApiDemo.models.*;
import com.dylan.projet.ApiDemo.repositories.AddressRepository;
import com.dylan.projet.ApiDemo.repositories.UserRepository;
import com.dylan.projet.ApiDemo.services.interfaces.AccountService;
import com.dylan.projet.ApiDemo.services.interfaces.UserService;
import com.dylan.projet.ApiDemo.utils.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final AccountService accountService;

    private final AddressRepository addressRepository;

    private final ObjectValidator<User> validator;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;


    @Override
    public Integer save(User model) {
        model.setActive(false);
        validator.validate(model);
        UserEntity userEntity = User.toEntity(model);
        userEntity.setPassword(passwordEncoder.encode(model.getPassword()));
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
    public AuthResponse register(User model) {
        model.setActive(false);
        validator.validate(model);
        UserEntity userEntity = User.toEntity(model);
        userEntity.setPassword(passwordEncoder.encode(model.getPassword()));
        userEntity.setRole("USER");
        repository.save(userEntity);
        Map<String, Object> claims = Map.of("userId", userEntity.getId(), "fullname", userEntity.getFirstName() + " " + userEntity.getLastName());
        String token = jwtUtils.generateToken(userEntity, claims);

        return AuthResponse.builder()
                .token(token)
                .build();
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
        UserEntity userEntity = repository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return User.fromEntity(userEntity);
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


    @Override
    public AuthResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        final UserEntity user = repository.findByEmail(authRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        Map<String, Object> claims = Map.of("userId", user.getId(), "fullname", user.getFirstName() + " " + user.getLastName());
        String token = jwtUtils.generateToken(user, claims);
        return AuthResponse.builder().token(token).build();
    }


}