package com.projet.escalade.controller;

import com.projet.escalade.repository.TopoRepository;
import com.projet.escalade.service.ReservationService;
import com.projet.escalade.service.SecurityService;
import com.projet.escalade.service.TopoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResevationController {


    @Autowired
    private SecurityService securityService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TopoService topoService;

    /**
     *
     * @param id id_top
     * @param model
     * création de la demande de réservation
     * @return
     */
    @GetMapping(value = "/reservation/demande")
    public String demandeReservation(@RequestParam(value = "id")int id,
                                     Model model)
    {

        model.addAttribute("reservation", reservationService.creationDemande(id, securityService.getNameUser()));

        model.addAttribute("topo",topoService.getTopoByID(id));

        return "reservation/demande";
    }


    @PostMapping(value = "/reservation/demande")
    public String demandePost(@RequestParam(value = "id")int id_reserv,
                              @RequestParam(value = "message")String m,
                              Model model)
    {
        //les topos disponibles
        model.addAttribute("liste", topoService.getTopoListByVisible());

        //alerte lors de la demande de reservation
        model.addAttribute("envoieDone", reservationService.valideDemande(id_reserv,m));
        return "intro/index";
    }


}
