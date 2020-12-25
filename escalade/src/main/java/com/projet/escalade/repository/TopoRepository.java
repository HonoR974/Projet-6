package com.projet.escalade.repository;

import com.projet.escalade.entity.Topo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface TopoRepository extends JpaRepository<Topo, Integer> {

    Topo findById(int id);

}
