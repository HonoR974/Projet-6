package com.projet.escalade.controller;

import com.projet.escalade.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller de l'espace personnel
 */
@Controller
public class EspaceController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TopoService topoService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private SiteService siteService;


    @Autowired
    private VoieService voieService;


    /**
     * Accueil de l'espace personnel de l'utilisateur
     * @param model model
     * @return espace/accueil
     */
    @GetMapping(value = "/espace/accueil")
    public String espaceAccueil(Model model)
    {

        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        model.addAttribute("liste", topoService.getTopoListByIdUser(userService.getIdUser(securityService.getNameUser())));

        return "espace/accueil";
    }




    /**
     * Espace Reservation
     * @param id_user id_user
     * @param model model
     * @return espace/reservation
     */
    @GetMapping(value = "/espace/reservation")
    public String reservationGet(@RequestParam(value = "id")int id_user,
                                 Model model)
    {
        model.addAttribute("user", userService.getUserById(id_user));

        //Liste des reservation accepté
        model.addAttribute("listeAccepte", reservationService.getListReservAcceptByIdUser(id_user));

        //liste de reservation en attente
        model.addAttribute("listeReservation", reservationService.getListReservEnAttByIdUser(id_user));

        return "espace/reservation";
    }




    /**
     * L'utilisateur accepte une demande de reservation
     * @param id_reserv id_reservation
     * @param model model
     * @return espace/reservation
     */
    @PostMapping(value = "/espace/accepteReservation")
    public String reservationAccepete(@RequestParam(value = "id")int id_reserv,
                                      Model model)
    {

        /*
        * accepeter la reservation
        * changer le statut de la reservation
        * changer la visibilité du topo
        * ajouter le topo a la liste du demandeur
        * supprimer le topo au propriétaire
        *
         */
        reservationService.reservationAccepted(id_reserv);


        model.addAttribute("user", reservationService.getUserProprio(id_reserv));
        model.addAttribute("listeReservation", reservationService.getListReservEnAttByIdUser(userService.getIdUser(securityService.getNameUser())));


        return "espace/reservation";
    }


    //------------- Refuser la reservation ----------------//

    /**
     * L'utilisateur refuse une demande de réservation.
     * @param id id_reservation
     * @param model model
     * @return espace/reservation
     */
    @PostMapping(value = "/espace/refuseReservation")
    public String reservationRefuser(@RequestParam(value = "id")int id,
                                     Model model)
    {
        /*
         * refuser la demande
         * changer le statut de la resevation
         */
        reservationService.refuserReservation(id);

        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        //Liste des reservation accepté
        model.addAttribute("listeAccepte", reservationService.getListReservAcceptByIdUser(userService.getIdUser(securityService.getNameUser())));

        //liste de reservation en attente
        model.addAttribute("listeReservation", reservationService.getListReservEnAttByIdUser(userService.getIdUser(securityService.getNameUser())));

        return "espace/reservation";
    }



    //---------- Fin de la Réservation ---------------------//

    /**
     * L'utilisateur rend le topo
     * @param id_reservation id_reservation
     * @param id_user id_user
     * @param model model
     * @return espace/reservation
     */
    @PostMapping(value = "/espace/retourReservation")
    public String finReservation(@RequestParam(value = "id_res")int id_reservation,
                                 @RequestParam(value = "id_user")int id_user,
                                 Model model)
    {

        //le statut devient "fini" le topo devient visible
        reservationService.finishReservation(id_reservation);

        model.addAttribute("user", userService.getUserById(id_user));

        //Liste des reservation accepté
        model.addAttribute("listeAccepte", reservationService.getListReservAcceptByIdUser(id_user));

        //liste de reservation en attente
        model.addAttribute("listeReservation", reservationService.getListReservEnAttByIdUser(id_user));

        return "espace/reservation";
    }


    /**
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/espace/topoReserve")
    public String detailTopoReserve(@RequestParam(value = "id")int id,
                                    Model model)
    {

        model.addAttribute("user", topoService.getUserById(id));

        model.addAttribute("topo", topoService.getTopoByID(id));

        model.addAttribute("listeSite", topoService.sendSiteByTopo(id));

        return "espace/reservationTopo";
    }

    @GetMapping(value = "/espace/detailSite")
    public String detailSite(@RequestParam(value = "id")int id,
                             Model model)
    {

        //pour afficher les infos du site
        model.addAttribute("site", siteService.getSiteById(id));

        //pour afficher les voies du site
        model.addAttribute("listeVoie", siteService.getVoieBySite(id));

        return "espace/reservationSite";
    }

    @GetMapping(value = "/espace/detailVoie")
    public String detailVoie(@RequestParam(value = "id")int id,
                             Model model)
    {
        model.addAttribute("voie", voieService.getVoieById(id));
        model.addAttribute("site", voieService.getSiteByIdVoie(id));
        model.addAttribute("user", securityService.getUser());
        return "espace/reservationVoie";
    }


}
