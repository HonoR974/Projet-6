package com.projet.escalade.service;

import com.projet.escalade.entity.Reservation;
import com.projet.escalade.entity.User;

import java.util.List;

public interface ReservationService {

    Reservation creationDemande(int id_topo,String name_user);

    boolean valideDemande(int id_reser, String message);

    List<Reservation> getListReservEnAttByIdUser(int id_user);

    List<Reservation> getListReservAcceptByIdUser(int id_user);

    void reservationAccepted(int id_reserv);

    User getUserProprio(int id_reserv);

    void finishReservation(int id_reserv);
}
