package com.projet.escalade.repository;

import com.projet.escalade.entity.Reservation;
import com.projet.escalade.entity.Statut;
import com.projet.escalade.entity.Topo;
import com.projet.escalade.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository des reservations
 */
@Repository
public interface ReservationRepository  extends JpaRepository<Reservation,Integer> {

    /**
     * Trouve une reservation par son id
     * @param id id_reservation
     * @return reservation
     */
    Reservation findById(int id);

    /**
     * Trouve des reservations par le statut et l'utilisateur
     * @param statut statut de la reservatin
     * @param user utilisateur
     * @return liste de reservation
     */
    List<Reservation> findByStatutAndUser( Statut statut,User user);

    /**
     * Trouve des reservations par le statut et le topo
     * @param statut statut de la reservation
     * @param topo topo
     * @return liste de reservation
     */
    List<Reservation> findByStatutAndTopo( Statut statut, Topo topo);

}
