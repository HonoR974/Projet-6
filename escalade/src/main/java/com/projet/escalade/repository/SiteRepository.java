package com.projet.escalade.repository;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Topo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository des sites
 */
@Repository
public interface SiteRepository  extends JpaRepository<Site,Integer> {

    /**
     * Trouve un site par son id
     * @param id id_site
     * @return site
     */
    Site findById(int id);

    /**
     * Trouve une liste de site par le topo
     * @param topo topo
     * @return liste de site
     */
    List<Site>  findByTopo(Topo topo);

    /**
     * Trouve par le nom ou la region - igonre les majuscule et minuscule
     * @param nom nom
     * @param region region
     * @return liste de site
     */
    List<Site> findByNomIgnoreCaseOrRegionIgnoreCase(String nom, String region);

    List<Site> findByVisible(boolean b);

}
