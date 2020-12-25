package com.projet.escalade.repository;

import com.projet.escalade.entity.Voie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoieRepository extends JpaRepository<Voie, Integer> {

    Voie findById(int id);
}
