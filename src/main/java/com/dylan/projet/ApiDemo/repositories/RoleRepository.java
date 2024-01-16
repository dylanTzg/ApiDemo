package com.dylan.projet.ApiDemo.repositories;

import com.dylan.projet.ApiDemo.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
}