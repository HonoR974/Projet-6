package com.projet.escalade.service;

import com.projet.escalade.entity.Reservation;
import com.projet.escalade.entity.Statut;
import com.projet.escalade.entity.Topo;
import com.projet.escalade.entity.User;
import com.projet.escalade.repository.ReservationRepository;
import com.projet.escalade.repository.StatutRepository;
import com.projet.escalade.repository.TopoRepository;
import com.projet.escalade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * ReservationServiceImpl
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TopoRepository topoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatutRepository statutRepository;

    /**
     * Creer une demande de réservation
     * <p>La réservation contient le topo et le nom de l'utilisateur </p>
     * @param id_top id_topo
     * @param name_user username;
     * @return reservation
     */
    @Override
    public Reservation creationDemande(int id_top, String name_user)
    {
        Topo t = topoRepository.findById(id_top);
        User u = userRepository.findByUsername(name_user);

        Reservation r = new Reservation();

        r.setTopo(t);
        r.setUser(u);
        reservationRepository.save(r);
        return r;
    }

    /**
     * Return une liste de reservation par un statut et un user
     * <p>Le statut sera "ACCEPTE" </p>
     * @param id_user id_user
     * @return liste de reservation
     */
    @Override
    public  List<Reservation> getListReservAcceptByIdUser(int id_user)
    {
        User user = userRepository.findById(id_user);
        Statut statut = statutRepository.findByName("ACCEPTE");


        return reservationRepository.findByStatutAndUser(statut,user);
    }



    /**
     * Return une liste de reservation par un statut et un user
     * <p>Le statut sera "EN_ATTENTE"</p>
     * @param id_user id_user
     * @return
     */
    @Override
    public List<Reservation> getListReservEnAttByIdUser(int id_user)
    {
        User user = userRepository.findById(id_user);
        List<Topo> topoList = user.getTopos();

        Statut statut = statutRepository.findByName("EN_ATTENTE");

        List<Reservation> reservationList =  new ArrayList<>();
        List<Reservation> boucleList ;


        for (Topo topo : topoList)
        {

           //si ce topo a un statut "en attente"
            boucleList = reservationRepository.findByStatutAndTopo(statut,topo);

            int i = 0 ;
            //ajoute tout les reservations contenant ce topo
            while (i < boucleList.size())
            {
                reservationList.add(boucleList.get(i));
                i++;
            }
        }
        return reservationList;
    }

    /**
     * Return l'utisateur propriétaire du topo par l'id de la reservation
     * @param id_reserv  id_reservation
     * @return user
     */
    @Override
    public User getUserProprio(int id_reserv)
    {
        Reservation r = reservationRepository.findById(id_reserv);

        return r.getTopo().getUser();
    }


    /**
     * Validation de la demande de reservation
     *  <ul>
     *      <li>la demande est validé </li>
     *      <li>le message est sauvegardé </li>
     *      <li>le statut est en attente </li>
     *  </ul>
     * @param id_reser id_reservation
     * @param message message
     * @return true
     */
    @Override
    public boolean valideDemande(int id_reser, String message)
    {

        Reservation r = reservationRepository.findById(id_reser);
        r.setMessage(message);

        r.setStatut(statutRepository.findByName("EN_ATTENTE"));

        reservationRepository.save(r);

        return true;
    }

    /**
     * Le propriétaire du topo accepte la demande de réservation
     * <ul>
     *     <li>Le statut devient "ACCEPTE"</li>
     *     <li>le topo n'est plus visible ni disponible  </li>
     *     <li>Le demandeur recoit le topo</li>
     * </ul>
     * @param id_reserv id_reservation
     */
    @Override
    public void reservationAccepted(int id_reserv)
    {

        Reservation reservation = reservationRepository.findById(id_reserv);
        Statut statut = statutRepository.findByName("ACCEPTE");

        Topo topo = reservation.getTopo();

        User uDemandeur = reservation.getUser();
        User uProprietaire = topo.getUser();


        reservation.setStatut(statut);
        topo.setVisible(false);
        topo.setDisponible(false);


        userAddTopo(uDemandeur, topo);


        topoRepository.save(topo);
        userRepository.save(uDemandeur);
        userRepository.save(uProprietaire);
        reservationRepository.save(reservation);

    }

    /**
     * Le demandeur recoit le topo
     * @param user user_demandeur
     * @param topo top
     */
    public void userAddTopo(User user, Topo topo)
    {

       List<Topo> topoList = user.getTopos();

       topoList.add(topo);

       user.setTopos(topoList);

        userRepository.save(user);
    }


    /**
     * Le demandeur finit la reservation
     * <ul>
     *     <li>Le statut devient "FINI</li>
     *     <li>Le topo devient disponible </li>
     * </ul>
     * @param id_reserv reservation
     */
    @Override
    public void finishReservation(int id_reserv)
    {
        Reservation reservation = reservationRepository.findById(id_reserv);
        reservation.setStatut(statutRepository.findByName("FINI"));

        Topo topo = reservation.getTopo();
        topo.setDisponible(true);

        reservationRepository.save(reservation);
        topoRepository.save(topo);

    }

    /**
     * La reservation est refusé par le propriétaire
     * <p>Le statut devient "REFUSE"</p>
     * @param id_reserv id_reservation
     */
    @Override
    public void refuserReservation(int id_reserv)
    {
        Reservation reservation = reservationRepository.findById(id_reserv);
        Statut statut = statutRepository.findByName("REFUSE");
        reservation.setStatut(statut);
        reservationRepository.save(reservation);
    }

}
