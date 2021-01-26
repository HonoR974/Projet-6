package com.projet.escalade.repository;

import com.projet.escalade.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository des roles
 */
public interface RoleRepository extends JpaRepository<Role, Integer>{

    /**
     * Trouve un role par son nom
     * @param name name_role
     * @return role
     */
    Role findByName(String name);
}