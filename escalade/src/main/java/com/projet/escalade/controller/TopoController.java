package com.projet.escalade.controller;

import com.projet.escalade.entity.User;
import com.projet.escalade.service.SecurityService;
import com.projet.escalade.service.TopoService;
import com.projet.escalade.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller pour les topos
 */
@Controller
public class TopoController {


    @Autowired
    private TopoService topoService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    /**
     * Liste des topos de l'utilisateur
      * @param id_user id_user
     * @param model model
     * @return topo/liste
     */
    @RequestMapping(value = "/topo/liste", method = RequestMethod.GET)
    public String topoListe(@RequestParam(value = "id")int id_user,
            Model model)
    {
        model.addAttribute("liste", topoService.getTopoListByIdUser(id_user));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "topo/liste";
    }


    /**
     * La page pour l'ajout d'un topo
     * @param id_user id_user
     * @param model model
     * @return topo/ajout
     */
    @RequestMapping(value = "/topo/ajout", method = RequestMethod.GET)
    public String topoAjoutGet(@RequestParam(value = "id")int id_user,
                    Model model)
    {
        model.addAttribute("topo", topoService.createTopo(id_user));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "topo/ajout";
    }

    /**
     * Ajout d'un topo
     * @param id id_topo
     * @param visible visibilité
     * @param nom nom_topo
     * @param description description_topo
     * @param date date_topo
     * @param model model
     * @return topoo/recapAdd
     */
    @RequestMapping(value = "/topo/ajout", method = RequestMethod.POST)
    public String topoAjoutPost(@RequestParam(value = "id")int id,
                                @RequestParam(value = "visible",required = false)boolean visible,
                                @RequestParam(value = "nom", required = false)String nom,
                                @RequestParam(value = "description", required = false)String description,
                                @RequestParam(value = "date_creation", required = false)
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                 Model model)
    {

        model.addAttribute("topo", topoService.saveTopo(id,visible,nom,description,date));
        model.addAttribute("user", topoService.getUserByIdTopo(id));
        return "topo/recapAdd";
    }


    /**
     * La page detail d'un topo
     * @param id id_topo
     * @param model model
     * @return topo/detail
     */
    @RequestMapping(value = "/topo/detail", method = RequestMethod.GET)
    public String detail(@RequestParam(value = "id")int id, Model model)
    {
        model.addAttribute("user", topoService.getUserByIdTopo(id));

        model.addAttribute("topo", topoService.getTopoByIdTopo(id));

         model.addAttribute("listeSite", topoService.sendSiteByTopo(id));

        return "topo/detail";
    }


    /**
     * La page pour modifier un topo
     * @param id id_topo
     * @param model model
     * @return topo/modif
     */
    @RequestMapping(value = "/topo/modif",method = RequestMethod.GET)
    public String modifGet(@RequestParam(value = "id")int id, Model model)
    {
        model.addAttribute("topo", topoService.getTopoByIdTopo(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "topo/modif";
    }

    /**
     * Modification du topo
     * @param id id_topo
     * @param visible visible
     * @param nom nom_topo
     * @param des description
     * @param model model
     * @return topo/detail
     */
    @RequestMapping(value = "/topo/modif",method = RequestMethod.POST)
    public String modifPost(@RequestParam(value = "id")int id,
                            @RequestParam(value = "visible",required = false)boolean visible,
                            @RequestParam(value = "nom", required = false)String nom,
                            @RequestParam(value = "description", required = false)String des,
                            Model model)
    {
        model.addAttribute("topo", topoService.updateTopo(id,visible,nom,des));

        model.addAttribute("user", topoService.getUserByIdTopo(id));
        model.addAttribute("listeSite", topoService.sendSiteByTopo(id));

        return "topo/detail";
    }

    /**
     * La page pur supprimer un topo
     * @param id id_topo
     * @param model model
     * @return topo/supprime
     */
    @RequestMapping(value = "/topo/supprime",method = RequestMethod.GET)
    public String supprGet(@RequestParam(value = "id")int id, Model model)
    {
        model.addAttribute("topo", topoService.getTopoByIdTopo(id));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "topo/supprime";
    }

    /**
     * Suppression d'un topo
     * @param id id_topo
     * @param model model
     * @return topo/liste
     */
    @RequestMapping(value = "/topo/supprime",method = RequestMethod.POST)
    public String supprPost(@RequestParam(value = "id")int id, Model model)
    {
        User u = topoService.getUserByIdTopo(id);


        topoService.deleteById(id);

        model.addAttribute("liste", topoService.getTopoListByIdUser(u.getId()));
        return "topo/liste";
    }
    
}