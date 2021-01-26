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

/**
 * Controller pour les sites
 */
@Controller
public class SiteController {

    @Autowired
    private SiteService siteService;


    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;


    /**
     * La page de la liste des site
     * @param  id_topo id_topo contenant les sites
     * @param model model
     * @return site/liste
     */
    @RequestMapping(value = "/site/liste", method = RequestMethod.GET)
    public String siteListe(@RequestParam(value = "id")int id_topo,
            Model model)
    {
        model.addAttribute("liste", siteService.getSiteListByIdTopo( id_topo));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        model.addAttribute("topo", siteService.getTopoByIdSite(id_topo));

        return "site/liste";
    }


    /**
     * La page detail d'un site
     * @param id id_site
     * @param model model
     * @return site/detail
     */
    @RequestMapping(value = "/site/detail", method = RequestMethod.GET)
    public String detail(@RequestParam(value = "id")int id, Model model)
    {
        //pour afficher les infos du site
        model.addAttribute("site", siteService.getSiteByIdSite(id));


       //pour afficher les voies du site
        model.addAttribute("listeVoie", siteService.getVoieByIdSite(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "site/detail";
    }


    /**
     * Ajout d'un site
     * @param id id_site
     * @param model model
     * @return site/ajout
     */
    @RequestMapping(value = "/site/ajout", method = RequestMethod.GET)
    public String ajoutGet(@RequestParam(value = "id")int id,  Model model)
    {
        model.addAttribute("site", siteService.createSite());

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

    /**
     * La page de mofication du site
     * @param id id_site
     * @param model model
     * @return site/modif
     */
    @RequestMapping(value = "/site/modif", method = RequestMethod.GET)
    public String modifGet(@RequestParam(value = "id")int id, Model model)
    {
        model.addAttribute("site", siteService.getSiteByIdSite(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "site/modif";
    }

    /**
     * Modification du site
     * @param id id_site
     * @param visible visibilité du site
     * @param region region
     * @param nom nom du site
     * @param adr adresse du site
     * @param model model
     * @return site/detail
     */
    @RequestMapping(value = "/site/modif", method = RequestMethod.POST)
    public String modifPost(@RequestParam(value = "id")int id,
                            @RequestParam(value = "visible")boolean visible,
                            @RequestParam(value = "region")String region ,
                            @RequestParam(value = "nom")String nom ,
                            @RequestParam(value = "adresse")String adr ,
                            Model model)
    {
       model.addAttribute("site", siteService.updateSite(id, visible, region, nom, adr));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "site/detail";
    }


    /**
     * La page pour supprimer un site
     * @param id id_site
     * @param model model
     * @return site/supprimer
     */
    @RequestMapping(value = "/site/supprime", method = RequestMethod.GET)
    public String supprimGet(@RequestParam(value = "id")int id,
                             Model model)
    {
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        model.addAttribute("site", siteService.getSiteByIdSite(id));
        return "site/supprime";
    }

    /**
     * Suppression du site
     * @param id id_site
     * @param model model
     * @return site/liste
     */
    @RequestMapping(value = "/site/supprime", method = RequestMethod.POST)
    public String supprimePost(@RequestParam(value = "id")int id, Model model)
    {
         int  id_topo = siteService.getIdTopoByIdSite(id);

        siteService.deleteById(id);
        model.addAttribute("liste", siteService.getSiteListByIdTopo(id_topo));
        model.addAttribute("topo", siteService.getTopoByIdTopo(id_topo));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "site/liste";
    }



}
