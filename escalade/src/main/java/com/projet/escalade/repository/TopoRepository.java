package com.projet.escalade.repository;

import com.projet.escalade.entity.Topo;
import com.projet.escalade.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository des topos
 */
@Repository
public interface TopoRepository extends JpaRepository<Topo, Integer> {

    /**
     * Trouve un topo par son id
     * @param id id_topo
     * @return topo
     */
    Topo findById(int id);


    /**
     * Trouve une liste de topo par leur visibilité
     * @param visible visible
     * @return liste de topos
     */
    List<Topo> findTopoByVisible(boolean visible );



}
