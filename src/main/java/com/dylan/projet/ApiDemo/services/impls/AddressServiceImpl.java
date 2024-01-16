package com.dylan.projet.ApiDemo.services.impls;

import com.dylan.projet.ApiDemo.models.Address;
import com.dylan.projet.ApiDemo.repositories.AddressRepository;
import com.dylan.projet.ApiDemo.services.interfaces.AddressService;
import com.dylan.projet.ApiDemo.utils.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    private final ObjectValidator<Address> validator;

    @Override
    public Integer save(Address model) {
        validator.validate(model);
        return repository.save(Address.toEntity(model)).getId();
    }

    @Override
    public List<Address> findAll() {
        return repository.findAll()
                .stream()
                .map(Address::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Address findById(Integer id) {
        return repository.findById(id)
                .map(Address::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
