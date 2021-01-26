package com.projet.escalade.repository;

import com.projet.escalade.entity.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository des statuts
 */
@Repository
public interface StatutRepository extends JpaRepository<Statut, Integer> {

    /**
     * Trouve un statut par le nom
     * @param name nom
     * @return statut
     */
    Statut findByName(String name);
}
