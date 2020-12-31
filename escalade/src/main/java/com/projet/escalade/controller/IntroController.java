package com.projet.escalade.controller;

import com.projet.escalade.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IntroController {

    @Autowired
    private TopoService topoService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private VoieService voieService;

    @Autowired
    private CommentaireService commentaireService;



    //----------- Intro --------------//
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model)
    {
        model.addAttribute("liste", topoService.getTopoListByVisible());

        return "intro/index";
    }

    //-------------- Detail Topo -----------//
    @GetMapping(value = "/intro/detailTopo")
    public String detailTopo(@RequestParam(value = "id")int id,
                             Model model)
    {
        model.addAttribute("topo", topoService.getTopoByID(id));
        model.addAttribute("liste", topoService.sendSiteByTopo(id));
        return "intro/detailTopo";
    }

    //------------- Detail Site ------------//
    @GetMapping(value = "/intro/detailSite")
    public String detailSite(@RequestParam(value = "id")int id,
                             Model model)
    {
        model.addAttribute("site", siteService.getSiteById(id));
        model.addAttribute("liste", siteService.getVoieBySite(id));
        model.addAttribute("topo", siteService.getTopoByIdSite(id));
        model.addAttribute("commentaires", siteService.getCommentaireListByIdSite(id));
        model.addAttribute("comm", commentaireService.createComment());
        return "intro/detailSite";
    }


    //--------------- Detail Voie ----------//
    @GetMapping(value = "/intro/detailVoie")
    public String detailVoie(@RequestParam(value = "id")int id,
                             Model model)
    {
        model.addAttribute("voie", voieService.getVoieById(id));
        model.addAttribute("site", voieService.getSiteByIdVoie(id));
        return "intro/detailVoie";
    }


}
