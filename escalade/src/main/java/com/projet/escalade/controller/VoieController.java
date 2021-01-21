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


@Controller
public class VoieController {

    @Autowired
    private VoieService voieService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;



    //-------------- Liste -------------//
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


    //-----------Detail -----------//
    @RequestMapping(value = "/voie/detail", method = RequestMethod.GET)
    public String detail(@RequestParam(value = "id")int id , Model model)
    {
        model.addAttribute("voie", voieService.getVoieById(id));
        model.addAttribute("site", voieService.getSiteByIdVoie(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "voie/detail";
    }


    //----------------- Ajout -------------//
    @RequestMapping(value = "/voie/ajout", method = RequestMethod.GET)
    public String ajoutGet(@RequestParam(value = "id")int id ,
            Model model)
    {

        //je creer une voie
        model.addAttribute("voie", voieService.createVoie(id));
        //le site de la voie
        model.addAttribute("site", voieService.getSiteByIdSite(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "voie/ajout";
    }

    @RequestMapping(value = "/voie/ajout", method = RequestMethod.POST)
    public String ajoutPost(@RequestParam(value = "id")int id_site,
                            @RequestParam(value = "nom")String n,
                            @RequestParam(value = "cotation")String c,
            Model model)
    {
        model.addAttribute("voie", voieService.saveVoie(n,c, id_site));

        model.addAttribute("site", voieService.getSiteByIdSite(id_site));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "voie/recapAdd";
    }



    //--------------------- Update ----------------//
    @RequestMapping(value = "/voie/modif", method = RequestMethod.GET)
    public String modifGet(@RequestParam(value = "id")int id,
                           Model model)
    {
        model.addAttribute("voie", voieService.getVoieById(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "voie/modif";
    }

    @RequestMapping(value = "/voie/modif", method = RequestMethod.POST)
    public String modifPost(@RequestParam(value = "id")int id,
                           @RequestParam(value = "nom")String n,
                           @RequestParam(value = "cotation")String c,
                           Model model)
    {

        model.addAttribute("voie", voieService.updateVoie(id,n,c));;

        model.addAttribute("site", voieService.getSiteByIdVoie(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "voie/detail";
    }



    //--------------------- Delete -------------------//
    @RequestMapping(value = "/voie/delete", method = RequestMethod.GET)
    public String deleteGet(@RequestParam(value = "id")int id, Model model)
    {
        model.addAttribute("voie", voieService.getVoieById(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "voie/supprime";
    }

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
