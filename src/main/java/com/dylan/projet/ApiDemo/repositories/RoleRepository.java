package com.dylan.projet.ApiDemo.repositories;

import com.dylan.projet.ApiDemo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}