package com.projet.escalade.repository;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Topo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository  extends JpaRepository<Site,Integer> {

    Site findById(int id);

    List<Site>  findByTopo(Topo topo);

    List<Site> findByNomIgnoreCaseOrRegionIgnoreCase(String nom, String region);

    List<Site> findByVisible(boolean b);

}
