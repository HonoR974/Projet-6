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

@Controller
public class TopoController {


    @Autowired
    private TopoService topoService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    //-------------   Liste  --------------//
    // Besoin de ( listeTopo & user )

    @RequestMapping(value = "/topo/liste", method = RequestMethod.GET)
    public String topoListe(@RequestParam(value = "id")int id_user,
            Model model)
    {
        model.addAttribute("liste", topoService.getTopoListByIdUser(id_user));
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        return "topo/liste";
    }



    //------------ Ajout Topo  --------------//
    @RequestMapping(value = "/topo/ajout", method = RequestMethod.GET)
    public String topoAjoutGet(@RequestParam(value = "id")int id_user,
                    Model model)
    {
        model.addAttribute("topo", topoService.createTopo(id_user));
        return "topo/ajout";
    }

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
        model.addAttribute("user", topoService.getUserById(id));
        return "topo/recapAdd";
    }


    //------------- detail ------//
    // Besoin : ( Topo , User , ListeSite )
    @RequestMapping(value = "/topo/detail", method = RequestMethod.GET)
    public String detail(@RequestParam(value = "id")int id, Model model)
    {
        model.addAttribute("user", topoService.getUserById(id));

        model.addAttribute("topo", topoService.getTopoByID(id));

         model.addAttribute("listeSite", topoService.sendSiteByTopo(id));

        return "topo/detail";
    }

    //------------------ modification ------------//
    @RequestMapping(value = "/topo/modif",method = RequestMethod.GET)
    public String modifGet(@RequestParam(value = "id")int id, Model model)
    {
        model.addAttribute("topo", topoService.getTopoByID(id));
        return "topo/modif";
    }


    @RequestMapping(value = "/topo/modif",method = RequestMethod.POST)
    public String modifPost(@RequestParam(value = "id")int id,
                            @RequestParam(value = "visible",required = false)boolean v,
                            @RequestParam(value = "nom", required = false)String n,
                            @RequestParam(value = "description", required = false)String d,
                            Model model)
    {
        model.addAttribute("topo", topoService.updateTopo(id,v,n,d));
        return "topo/detail";
    }

    //-----------------------  Delete ---------------//
    @RequestMapping(value = "/topo/supprime",method = RequestMethod.GET)
    public String supprGet(@RequestParam(value = "id")int id, Model model)
    {
        model.addAttribute("topo", topoService.getTopoByID(id));
        return "topo/supprime";
    }

    @RequestMapping(value = "/topo/supprime",method = RequestMethod.POST)
    public String supprPost(@RequestParam(value = "id")int id, Model model)
    {
        User u = topoService.getUserById(id);


        topoService.deleteById(id);

        model.addAttribute("liste", topoService.getTopoListByIdUser(u.getId()));
        return "topo/liste";
    }


}
