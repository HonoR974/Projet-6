package com.projet.escalade.repository;

import com.projet.escalade.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{

    Role findByName(String name);
}