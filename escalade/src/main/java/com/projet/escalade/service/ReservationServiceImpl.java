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
     *
     * @param id_reser
     * @param message
     * la demande est validée
     * le message est sauvegardé
     * le statut est en attente
     * @return
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

    @Override
    public List<Reservation> getListReservEnAttByIdUser(int id_user)
    {
        User u = userRepository.findById(id_user);
        List<Topo> topoList = u.getTopos();

        Statut s = statutRepository.findByName("EN_ATTENTE");

        List<Reservation> reservationList =  new ArrayList<>();
        List<Reservation> boucleList ;


        for (Topo topo : topoList)
        {
           //si ce topo a un statut
            boucleList = reservationRepository.findByStatutAndTopo(s,topo);

            int i = 0 ;

            while (i < boucleList.size())
            {
                reservationList.add(boucleList.get(i));
                i++;
            }
        }
        return reservationList;
    }

    /**
     *
     * @param id_reserv
     *
     * 1 - Changer le statut
     * 2 -
     */
    @Override
    public void reservationAccepted(int id_reserv)
    {

        Reservation reservation = reservationRepository.findById(id_reserv);
        Statut statut = statutRepository.findByName("ACCEPTE");

        Topo topo = reservation.getTopo();
        User uDemandeur = reservation.getUser();
        User uProprietaire = topo.getUser();


        updateStatut(reservation,statut);

        topo.setVisible(false);
        topo.setDisponible(false);

        userAddTopo(uDemandeur, topo);




        topoRepository.save(topo);
        userRepository.save(uDemandeur);
        userRepository.save(uProprietaire);
        reservationRepository.save(reservation);

    }

    public void updateStatut(Reservation r , Statut s )
    {
        r.setStatut(s);
        reservationRepository.save(r);
    }

    public void userAddTopo(User u, Topo topo)
    {

       List<Topo> topoList = u.getTopos();

       topoList.add(topo);

       u.setTopos(topoList);

        userRepository.save(u);
    }

    @Override
    public User getUserProprio(int id_reserv)
    {
        Reservation r = reservationRepository.findById(id_reserv);

        return r.getTopo().getUser();
    }

    @Override
    public  List<Reservation> getListReservAcceptByIdUser(int id_user)
    {
        User user = userRepository.findById(id_user);
        Statut statut = statutRepository.findByName("ACCEPTE");


        return reservationRepository.findByStatutAndUser(statut,user);
    }

    /**
     *
     * @param id_reserv reservation
     * le statut vas devenir  "fini"
     * le topo vas etre visible
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

}
