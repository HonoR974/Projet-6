package com.projet.escalade.controller;

import com.projet.escalade.service.TopoService;
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

    //-------------   Liste  --------------//
    @RequestMapping(value = "/topo/liste", method = RequestMethod.GET)
    public String topoListe(Model model)
    {
        model.addAttribute("liste", topoService.getTopoList());
        return "topo/liste";
    }



    //------------ Ajout Topo  --------------//
    @RequestMapping(value = "/topo/ajout", method = RequestMethod.GET)
    public String topoAjoutGet(Model model)
    {
        model.addAttribute("topo", topoService.createTopo());
        return "topo/ajout";
    }

    @RequestMapping(value = "/topo/ajout", method = RequestMethod.POST)
    public String topoAjoutPost(@RequestParam(value = "visible",required = false)boolean visible,
                                @RequestParam(value = "nom", required = false)String nom,
                                @RequestParam(value = "description", required = false)String description,
                                @RequestParam(value = "date_creation", required = false)
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                 Model model)
    {

        model.addAttribute("topo", topoService.saveTopo(visible,nom,description,date));
        return "topo/recapAdd";
    }


    //------------- detail ------//
    @RequestMapping(value = "/topo/detail", method = RequestMethod.GET)
    public String detail(@RequestParam(value = "id")int id, Model model)
    {
        model.addAttribute("topo", topoService.getTopoByID(id));

        //
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
        topoService.deleteById(id);
        model.addAttribute("liste", topoService.getTopoList());
        return "topo/liste";
    }


}
