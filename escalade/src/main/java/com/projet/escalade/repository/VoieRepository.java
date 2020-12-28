package com.projet.escalade.repository;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Voie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoieRepository extends JpaRepository<Voie, Integer> {

    Voie findById(int id);

    List<Voie> findBySite(Site s);
}
