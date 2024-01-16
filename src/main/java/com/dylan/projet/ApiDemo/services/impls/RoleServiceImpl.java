package com.dylan.projet.ApiDemo.services.impls;

import com.dylan.projet.ApiDemo.models.Role;
import com.dylan.projet.ApiDemo.repositories.RoleRepository;
import com.dylan.projet.ApiDemo.services.interfaces.RoleService;
import com.dylan.projet.ApiDemo.utils.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    private final ObjectValidator<Role> validator;
    @Override
    public Integer save(Role model) {
        validator.validate(model);
        return repository.save(Role.toEntity(model)).getId();
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll()
                .stream()
                .map(Role::fromEntity)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Role findById(Integer id) {
        return repository.findById(id)
                .map(Role::fromEntity)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Role not found"));
    }

    @Override
    public void deleteById(Integer id) {
            repository.deleteById(id);
    }
}
