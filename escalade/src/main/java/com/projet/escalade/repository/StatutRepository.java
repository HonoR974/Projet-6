package com.projet.escalade.repository;

import com.projet.escalade.entity.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutRepository extends JpaRepository<Statut, Integer> {

    Statut findByName(String name);
}
