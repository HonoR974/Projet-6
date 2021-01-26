package com.projet.escalade.repository;

import com.projet.escalade.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository des utilisateur
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Trouve un user par son nom
     * @param username username
     * @return  user
     */
    User findByUsername(String username);

    /**
     * Trouve un user par son id
     * @param id id_user
     * @return user
     */
    User findById(int id);

}