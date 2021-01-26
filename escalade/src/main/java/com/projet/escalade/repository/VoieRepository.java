package com.projet.escalade.repository;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Voie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository des voies
 */
public interface VoieRepository extends JpaRepository<Voie, Integer> {

    /**
     * Trouve une voie par son id
     * @param id id_voie
     * @return voie
     */
    Voie findById(int id);

    /**
     * Trouve une liste de voie par le site
     * @param site
     * @return liste de site
     */
    List<Voie> findBySite(Site site);
}
