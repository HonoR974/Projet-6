package com.projet.escalade.service;

import com.projet.escalade.entity.Reservation;
import com.projet.escalade.entity.User;

import java.util.List;

/**
 * Interface ReservationService
 */
public interface ReservationService {


    /**
     * Creer une demande de réservation

     * @param id_topo id_topo
     * @param name_user username;
     * @return reservation
     */
    Reservation creationDemande(int id_topo,String name_user);


    /**
     * Return une liste de reservation par un statut et un user
     * @param id_user id_user
     * @return
     */
    List<Reservation> getListReservEnAttByIdUser(int id_user);

    /**
     * Return une liste de reservation par un statut et un user
     * @param id_user id_user
     * @return liste de reservation
     */
    List<Reservation> getListReservAcceptByIdUser(int id_user);

    /**
     * Return l'utisateur propriétaire du topo par l'id de la reservation
     * @param id_reserv  id_reservation
     * @return user
     */
    User getUserProprio(int id_reserv);


    /**
     * Validation de la demande de reservation
     * @param id_reser id_reservation
     * @param message message
     * @return true
     */
    boolean valideDemande(int id_reser, String message);

    /**
     * Le propriétaire du topo accepte la demande de réservation>
     * @param id_reserv id_reservation
     */
    void reservationAccepted(int id_reserv);

    /**
     * Le demandeur finit la reservation
     * @param id_reserv reservation
     */
    void finishReservation(int id_reserv);

    /**
     * La reservatin est refusé par le propriétaire
     * @param id_reserv id_reservation
     */
    void refuserReservation(int id_reserv);
}
