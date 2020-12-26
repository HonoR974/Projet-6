package com.projet.escalade.controller;

import com.projet.escalade.service.SiteService;
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

    private int id_topo;



    //-----------------Liste ----------//
    @RequestMapping(value = "/site/liste", method = RequestMethod.GET)
    public String siteListe(Model model)
    {
        model.addAttribute("liste", siteService.getSiteListByIdTop( id_topo));

        model.addAttribute("topo", siteService.getTopoById(id_topo));

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
        model.addAttribute("topo", siteService.getTopoById(id));
        return "site/ajout";
    }

    @RequestMapping(value = "/site/ajout", method = RequestMethod.POST)
    public String ajoutPost(@RequestParam("id")int id_topo,
            @RequestParam(value = "region")String r,
            @RequestParam(value = "nom")String n,
            @RequestParam(value = "adresse")String a,
            Model model)
    {

        model.addAttribute("site", siteService.saveSite(r,n,a,id_topo));
        return "site/recapAdd";

    }

    //---------------- Edition  ------------//
    @RequestMapping(value = "/site/modif", method = RequestMethod.GET)
    public String modifGet(@RequestParam(value = "id")int id, Model model)
    {
        model.addAttribute("site", siteService.getSiteById(id));

        return "site/modif";
    }

    @RequestMapping(value = "/site/modif", method = RequestMethod.POST)
    public String modifPost(@RequestParam(value = "id")int id,
                            @RequestParam(value = "region")String r ,
                            @RequestParam(value = "nom")String n ,
                            @RequestParam(value = "adresse")String a ,
                            Model model)
    {
       model.addAttribute("site", siteService.updateSite(id, r,n, a ));

       return "site/detail";
    }


    //---------------- Delete -------------//

    @RequestMapping(value = "/site/supprime", method = RequestMethod.GET)
    public String supprimGet(@RequestParam(value = "id")int id,
                             Model model)
    {
        model.addAttribute("site", siteService.getSiteById(id));
        return "site/supprime";
    }

    @RequestMapping(value = "/site/supprime", method = RequestMethod.POST)
    public String supprimePost(@RequestParam(value = "id")int id, Model model)
    {
        id_topo = siteService.getIdTopoByIdSite(id);

        siteService.deleteById(id);
        model.addAttribute("liste", siteService.getSiteListByIdTop(id_topo));
        return "site/liste";
    }



}
