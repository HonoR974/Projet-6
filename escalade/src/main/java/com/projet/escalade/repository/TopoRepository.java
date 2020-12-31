package com.projet.escalade.repository;

import com.projet.escalade.entity.Topo;
import com.projet.escalade.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TopoRepository extends JpaRepository<Topo, Integer> {

    Topo findById(int id);
    List<Topo> findByUser(User u);

    List<Topo> findTopoByVisible(boolean b );

}
