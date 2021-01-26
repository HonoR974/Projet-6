package com.projet.escalade.controller;

import com.projet.escalade.repository.TopoRepository;
import com.projet.escalade.service.ReservationService;
import com.projet.escalade.service.SecurityService;
import com.projet.escalade.service.SiteService;
import com.projet.escalade.service.TopoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller pour la demande de reservation
 */
@Controller
public class ResevationController {


    @Autowired
    private SecurityService securityService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TopoService topoService;

    @Autowired
    private SiteService siteService;

    /**
     *  Création de la demande de réservation
     * @param id id_top
     * @param model model
     * @return reservation/demande
     */
    @GetMapping(value = "/reservation/demande")
    public String demandeReservation(@RequestParam(value = "id")int id,
                                     Model model)
    {

        model.addAttribute("reservation", reservationService.creationDemande(id, securityService.getNameUser()));

        model.addAttribute("topo",topoService.getTopoByIdTopo(id));

        return "reservation/demande";
    }


    /**
     * La demande de reservation est valider
     * @param id_reserv id_reservation
     * @param message message envoyé pur l'échange de coordonné
     * @param model model
     * @return intro/index
     */
    @PostMapping(value = "/reservation/demande")
    public String demandePost(@RequestParam(value = "id")int id_reserv,
                              @RequestParam(value = "message")String message,
                              Model model)
    {
        //les topos disponibles
        model.addAttribute("listeTopo", topoService.getTopoListByVisible());
        model.addAttribute("listeSite", siteService.getSiteListByVisible());
        //alerte lors de la demande de reservation
        model.addAttribute("envoieDone", reservationService.valideDemande(id_reserv,message));
        return "intro/index";
    }


}
