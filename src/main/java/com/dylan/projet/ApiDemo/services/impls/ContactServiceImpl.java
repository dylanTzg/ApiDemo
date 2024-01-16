package com.dylan.projet.ApiDemo.services.impls;

import com.dylan.projet.ApiDemo.models.Contact;
import com.dylan.projet.ApiDemo.repositories.ContactRepository;
import com.dylan.projet.ApiDemo.services.interfaces.ContactService;
import com.dylan.projet.ApiDemo.utils.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;

    private final ObjectValidator<Contact> validator;

    @Override
    public Integer save(Contact model) {
        validator.validate(model);
        return repository.save(Contact.toEntity(model)).getId();
    }

    @Override
    public List<Contact> findAll() {
        return repository.findAll()
                .stream()
                .map(Contact::fromEntity)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Contact findById(Integer id) {
        return repository.findById(id)
                .map(Contact::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));
    }

    @Override
    public void deleteById(Integer id) {
          repository.deleteById(id);
    }
}
