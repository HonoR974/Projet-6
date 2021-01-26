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

/**
 * Controller de l'accueil , topo & site partagés
 */
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


    /**
     * La page d'accueil
     * @param model model
     * @return intro/index
     */
    @RequestMapping(value = {"/", "/intro/index"},  method = RequestMethod.GET)
    public String index(Model model)
    {
        model.addAttribute("listeTopo", topoService.getTopoListByVisible());
        model.addAttribute("listeSite", siteService.getSiteListByVisible());
        return "intro/index";
    }

    /**
     * Liste des topos partagés.
     * @param model model
     * @return intro/listTopo
     */
    @RequestMapping(value = "/intro/topo")
    public String topo(Model model)
    {
        model.addAttribute("liste", topoService.getTopoListByVisible());
        return "intro/listTopo";
    }

    /**
     * Liste des sites partagés
     * @param model model
     * @return intro/listeSite
     */
    @RequestMapping(value = "/intro/site")
    public String site(Model model)
    {
        model.addAttribute("listeSite", siteService.getSiteListByVisible());
        return "intro/listSite";
    }

    /**
     * La page d'un topo partagé
     * @param id id_topo
     * @param model model
     * @return intro/detailTopo
     */
    @GetMapping(value = "/intro/detailTopo")
    public String detailTopo(@RequestParam(value = "id")int id,
                             Model model)
    {
        model.addAttribute("topo", topoService.getTopoByIdTopo(id));
        model.addAttribute("liste", topoService.sendSiteByTopo(id));

        /*
            envoie la reponse si le topo appartient a l'utilisateur connecté
          pour afficher le btn ( demande de reservation )  ou pas
         */
        model.addAttribute("isMine", userService.topoIsMine(securityService.getNameUser(), id) );

        return "intro/detailTopo";
    }


    //------------- Detail Site ------------//

    /**
     * La page d'un site partagé
     * @param id id_site
     * @param model model
     * @return intro/detailSite
     */
    @GetMapping(value = "/intro/detailSite")
    public String detailSite(@RequestParam(value = "id")int id ,
                             Model model)
    {
        model.addAttribute("site", siteService.getSiteByIdSite(id));
        model.addAttribute("liste", siteService.getVoieByIdSite(id));
        model.addAttribute("topo", siteService.getTopoByIdSite(id));
        model.addAttribute("commentaires", siteService.getCommentaireListByIdSite(id));
        model.addAttribute("comm", commentaireService.createComment());
        return "intro/detailSite";
    }

    /**
     * La page d'une voie
     * @param id id_voie
     * @param model model
     * @return intro/detailVoie
     */
    @GetMapping(value = "/intro/detailVoie")
    public String detailVoie(@RequestParam(value = "id")int id,
                             Model model)
    {
        model.addAttribute("voie", voieService.getVoieByIdVoie(id));
        model.addAttribute("site", voieService.getSiteByIdVoie(id));
        return "intro/detailVoie";
    }


    /**
     * Un membre de l'association tague un site
     * @param id id_site
     * @param model model
     * @return intro/detailSite
     */
    @PostMapping(value = "/intro/siteTag")
    public String tagSite(@RequestParam(value = "id")int id,
                          Model model)
    {
        siteService.tagSite(id);

        model.addAttribute("site", siteService.getSiteByIdSite(id));
        model.addAttribute("liste", siteService.getVoieByIdSite(id));
        model.addAttribute("topo", siteService.getTopoByIdSite(id));
        model.addAttribute("commentaires", siteService.getCommentaireListByIdSite(id));
        model.addAttribute("comm", commentaireService.createComment());

        return "intro/detailSite";
    }





}
