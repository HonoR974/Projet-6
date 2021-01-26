package com.projet.escalade.controller;

import com.projet.escalade.entity.Voie;
import com.projet.escalade.service.SecurityService;
import com.projet.escalade.service.UserService;
import com.projet.escalade.service.VoieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller pour les voies
 */
@Controller
public class VoieController {

    @Autowired
    private VoieService voieService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;


    /**
     * La page de la liste des voie contenu dans le site
     * @param id id_site
     * @param model model
     * @return voie/liste
     */
    @RequestMapping(value = "/voie/liste", method = RequestMethod.GET)
    public String liste(@RequestParam(value = "id")int id,
            Model model)
    {
        model.addAttribute("liste", voieService.getListVoieByIdSite(id));

        //btn retour site
        model.addAttribute("site", voieService.getSiteByIdSite(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "voie/liste";
    }


    /**
     * La page detail d'une voie
     * @param id id_voie
     * @param model model
     * @return voie/detail
     */
    @RequestMapping(value = "/voie/detail", method = RequestMethod.GET)
    public String detail(@RequestParam(value = "id")int id , Model model)
    {
        model.addAttribute("voie", voieService.getVoieByIdVoie(id));
        model.addAttribute("site", voieService.getSiteByIdVoie(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "voie/detail";
    }


    /**
     * La page d'ajout d'une voie
     * @param id id_site
     * @param model model
     * @return voie/ajout
     */
    @RequestMapping(value = "/voie/ajout", method = RequestMethod.GET)
    public String ajoutGet(@RequestParam(value = "id")int id ,
            Model model)
    {


        model.addAttribute("voie", voieService.createVoie(id));
        //le site de la voie
        model.addAttribute("site", voieService.getSiteByIdSite(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "voie/ajout";
    }

    /**
     * Ajout d'une voie
     * @param id_site id_site
     * @param nom nom_voie
     * @param cota cotation
     * @param model model
     * @return voie/recapAdd
      */
    @RequestMapping(value = "/voie/ajout", method = RequestMethod.POST)
    public String ajoutPost(@RequestParam(value = "id")int id_site,
                            @RequestParam(value = "nom")String nom,
                            @RequestParam(value = "cotation")String cota,
            Model model)
    {
        model.addAttribute("voie", voieService.saveVoie(nom,cota, id_site));

        model.addAttribute("site", voieService.getSiteByIdSite(id_site));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "voie/recapAdd";
    }


    /**
     * La page pour modifier une voie
     * @param id id_voie
     * @param model model
     * @return voie/modif
     */
    @RequestMapping(value = "/voie/modif", method = RequestMethod.GET)
    public String modifGet(@RequestParam(value = "id")int id,
                           Model model)
    {
        model.addAttribute("voie", voieService.getVoieByIdVoie(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "voie/modif";
    }

    /**
     * Modification d'une voie
     * @param id id_voie
     * @param nom nom_voie
     * @param cot cotation
     * @param model model
     * @return voie/detail
     */
    @RequestMapping(value = "/voie/modif", method = RequestMethod.POST)
    public String modifPost(@RequestParam(value = "id")int id,
                           @RequestParam(value = "nom")String nom,
                           @RequestParam(value = "cotation")String cot,
                           Model model)
    {

        model.addAttribute("voie", voieService.updateVoie(id,nom,cot));;

        model.addAttribute("site", voieService.getSiteByIdVoie(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "voie/detail";
    }


    /**
     * La page pour supprimer une voie
     * @param id id_voie
     * @param model model
     * @return voie/supprime
     */
    @RequestMapping(value = "/voie/delete", method = RequestMethod.GET)
    public String deleteGet(@RequestParam(value = "id")int id, Model model)
    {
        model.addAttribute("voie", voieService.getVoieByIdVoie(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "voie/supprime";
    }

    /**
     * Suppression d'une voie
     * @param id id_voie
     * @param model model
     * @return voie/liste
     */
    @RequestMapping(value = "/voie/delete", method = RequestMethod.POST)
    public String deletePost(@RequestParam(value = "id")int id, Model model)
    {
        int id_site = voieService.getIdSiteByIdVoie(id);

        voieService.deleteById(id);
        model.addAttribute("liste", voieService.getListVoieByIdSite(id_site));
        model.addAttribute("site", voieService.getSiteByIdSite(id_site));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "voie/liste";
    }





}
