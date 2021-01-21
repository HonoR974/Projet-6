package com.projet.escalade.controller;

import com.projet.escalade.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;



    //----------- Intro --------------//
    @RequestMapping(value = {"/", "/intro/index"},  method = RequestMethod.GET)
    public String index(Model model)
    {
        model.addAttribute("listeTopo", topoService.getTopoListByVisible());
        model.addAttribute("listeSite", siteService.getSiteListByVisible());
        return "intro/index";
    }

    //--------------List Topo Visible ----------//
    @RequestMapping(value = "/intro/topo")
    public String topo(Model model)
    {
        model.addAttribute("liste", topoService.getTopoListByVisible());
        return "intro/listTopo";
    }

    @RequestMapping(value = "/intro/site")
    public String site(Model model)
    {
        model.addAttribute("listeSite", siteService.getSiteListByVisible());
        return "intro/listSite";
    }



    //-------------- Detail Topo -----------//
    @GetMapping(value = "/intro/detailTopo")
    public String detailTopo(@RequestParam(value = "id")int id,
                             Model model)
    {
        model.addAttribute("topo", topoService.getTopoByID(id));
        model.addAttribute("liste", topoService.sendSiteByTopo(id));

        //envoie la reponse si le topo appartient a l'uc
        //pour afficher le btn ( demande de reservation )  ou pas

        model.addAttribute("isMine", userService.topoIsMine(securityService.getNameUser(), id) );
        return "intro/detailTopo";
    }

    //------------- Detail Site ------------//
    @GetMapping(value = "/intro/detailSite")
    public String detailSite(@RequestParam(value = "id")int id ,
                             Model model)
    {
        model.addAttribute("site", siteService.getSiteById(id));
        model.addAttribute("liste", siteService.getVoieBySite(id));
        model.addAttribute("topo", siteService.getTopoByIdSite(id));
        model.addAttribute("commentaires", siteService.getCommentaireListByIdSite(id));
        model.addAttribute("comm", commentaireService.createComment());
        return "intro/detailSite";
    }

    //---------------- Tag ---------//
    @PostMapping(value = "/intro/siteTag")
    public String tagSite(@RequestParam(value = "id")int id,
                          Model model)
    {
        siteService.tagSite(id);

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
