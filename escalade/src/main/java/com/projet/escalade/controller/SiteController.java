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




    //-----------------Liste ----------//
    @RequestMapping(value = "/site/liste", method = RequestMethod.GET)
    public String siteListe(Model model)
    {
        model.addAttribute("liste", siteService.getSiteList());

        model.addAttribute("topo", siteService.backToTopo());

        return "site/liste";
    }


    //--------------- Detail -------------//
    @RequestMapping(value = "/site/detail", method = RequestMethod.GET)
    public String detail(@RequestParam(value = "id")int id, Model model)
    {
        //pour afficher les infos du site
        model.addAttribute("site", siteService.getSiteById(id));

       //pour afficher les voies du site
        model.addAttribute("listeVoie", siteService.getVoieBySite(id));

        return "site/detail";
    }



    //----------- Ajout -----------//
    @RequestMapping(value = "/site/ajout", method = RequestMethod.GET)
    public String ajoutGet(Model model)
    {
        model.addAttribute("site", siteService.createSite() );

        return "site/ajout";
    }

    @RequestMapping(value = "/site/ajout", method = RequestMethod.POST)
    public String ajoutPost(@RequestParam(value = "region")String r,
            @RequestParam(value = "nom")String n,
            @RequestParam(value = "adresse")String a,
            Model model)
    {

        model.addAttribute("site", siteService.saveSite(r,n,a));
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

        siteService.deleteById(id);
        model.addAttribute("liste", siteService.getSiteList());
        return "site/liste";
    }



}
