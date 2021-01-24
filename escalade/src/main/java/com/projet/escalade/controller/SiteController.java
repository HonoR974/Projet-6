package com.projet.escalade.controller;

import com.projet.escalade.service.SecurityService;
import com.projet.escalade.service.SiteService;
import com.projet.escalade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SiteController {

    @Autowired
    private SiteService siteService;


    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;


    private int id_topo;



    //-----------------Liste ----------//
    @RequestMapping(value = "/site/liste", method = RequestMethod.GET)
    public String siteListe(Model model)
    {
        model.addAttribute("liste", siteService.getSiteListByIdTop( id_topo));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        model.addAttribute("topo", siteService.getTopoByIdTopo(id_topo));

        return "site/liste";
    }


    //--------------- Detail -------------//
    @RequestMapping(value = "/site/detail", method = RequestMethod.GET)
    public String detail(@RequestParam(value = "id")int id, Model model)
    {
        //pour afficher les infos du site
        model.addAttribute("site", siteService.getSiteById(id));

        //je veux que la variable id_topo soit
        // la meme que celle dans le site de detail
        //on accede a detail par topo
        id_topo = siteService.getIdTopoByIdSite(id);

       //pour afficher les voies du site
        model.addAttribute("listeVoie", siteService.getVoieBySite(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "site/detail";
    }



    //----------- Ajout  -----------//
    @RequestMapping(value = "/site/ajout", method = RequestMethod.GET)
    public String ajoutGet(@RequestParam(value = "id")int id,  Model model)
    {
        model.addAttribute("site", siteService.createSite());

        //on donne la valeur a chaque site l'id de leur topo
        id_topo = id;

        //le topo qui ajoute ce site
        model.addAttribute("topo", siteService.getTopoByIdTopo(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "site/ajout";
    }

    @RequestMapping(value = "/site/ajout", method = RequestMethod.POST)
    public String ajoutPost(@RequestParam("id")int id_topo,
            @RequestParam(value = "visible", required = false)boolean visible ,
            @RequestParam(value = "region",required = false)String region,
            @RequestParam(value = "nom",required = false)String nom,
            @RequestParam(value = "adresse",required = false)String adresse,
            Model model)
    {

        model.addAttribute("site", siteService.saveSite(id_topo, visible,region, nom,adresse));
        model.addAttribute("topo", siteService.getTopoByIdTopo(id_topo));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "site/recapAdd";

    }

    //---------------- Edition  ------------//
    @RequestMapping(value = "/site/modif", method = RequestMethod.GET)
    public String modifGet(@RequestParam(value = "id")int id, Model model)
    {
        model.addAttribute("site", siteService.getSiteById(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "site/modif";
    }

    @RequestMapping(value = "/site/modif", method = RequestMethod.POST)
    public String modifPost(@RequestParam(value = "id")int id,
                            @RequestParam(value = "visible")boolean visible,
                            @RequestParam(value = "region")String r ,
                            @RequestParam(value = "nom")String n ,
                            @RequestParam(value = "adresse")String a ,
                            Model model)
    {
       model.addAttribute("site", siteService.updateSite(id, visible, r,n, a ));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "site/detail";
    }


    //---------------- Delete -------------//

    @RequestMapping(value = "/site/supprime", method = RequestMethod.GET)
    public String supprimGet(@RequestParam(value = "id")int id,
                             Model model)
    {
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        model.addAttribute("site", siteService.getSiteById(id));
        return "site/supprime";
    }

    @RequestMapping(value = "/site/supprime", method = RequestMethod.POST)
    public String supprimePost(@RequestParam(value = "id")int id, Model model)
    {
        id_topo = siteService.getIdTopoByIdSite(id);

        siteService.deleteById(id);
        model.addAttribute("liste", siteService.getSiteListByIdTop(id_topo));
        model.addAttribute("topo", siteService.getTopoByIdTopo(id_topo));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "site/liste";
    }



}
