package com.projet.escalade.repository;

import com.projet.escalade.entity.Reservation;
import com.projet.escalade.entity.Statut;
import com.projet.escalade.entity.Topo;
import com.projet.escalade.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation,Integer> {

    Reservation findById(int id);

    List<Reservation> findByStatutAndUser( Statut statut,User user);

    List<Reservation> findByStatutAndTopo( Statut statut, Topo topo);

}
