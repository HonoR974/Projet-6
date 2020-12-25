package com.projet.escalade.controller;

import com.projet.escalade.service.VoieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class VoieController {

    @Autowired
    private VoieService voieService;
    

    //-------------- Liste -------------//
    @RequestMapping(value = "/voie/liste", method = RequestMethod.GET)
    public String liste(Model model)
    {
        model.addAttribute("liste", voieService.getListVoie());
        return "voie/liste";
    }


    //-----------Detail -----------//
    @RequestMapping(value = "/voie/detail", method = RequestMethod.GET)
    public String detail(@RequestParam(value = "id")int id, Model model)
    {
        model.addAttribute("voie", voieService.getVoieById(id));

        return "voie/detail";
    }


    //----------------- Ajout -------------//
    @RequestMapping(value = "/voie/ajout", method = RequestMethod.GET)
    public String ajoutGet(Model model)
    {
        model.addAttribute("voie", voieService.createVoie());
        return "voie/ajout";
    }

    @RequestMapping(value = "/voie/ajout", method = RequestMethod.POST)
    public String ajoutPost(@RequestParam(value = "nom")String n,
                            @RequestParam(value = "cotation")String c,
            Model model)
    {
        model.addAttribute("voie", voieService.saveVoie(n,c));
        return "voie/recapAdd";
    }

    //--------------------- Update ----------------//
    @RequestMapping(value = "/voie/modif", method = RequestMethod.GET)
    public String modifGet(@RequestParam(value = "id")int id,
                           Model model)
    {
        model.addAttribute("voie", voieService.getVoieById(id));
        return "voie/modif";
    }

    @RequestMapping(value = "/voie/modif", method = RequestMethod.POST)
    public String modifPost(@RequestParam(value = "id")int id,
                           @RequestParam(value = "nom")String n,
                           @RequestParam(value = "cotation")String c,
                           Model model)
    {

        model.addAttribute("voie", voieService.updateVoie(id,n,c));
        return "voie/detail";
    }



    //--------------------- Delete -------------------//
    @RequestMapping(value = "/voie/delete", method = RequestMethod.GET)
    public String deleteGet(@RequestParam(value = "id")int id, Model model)
    {
        model.addAttribute("voie", voieService.getVoieById(id));
        return "voie/supprime";
    }

    @RequestMapping(value = "/voie/delete", method = RequestMethod.POST)
    public String deletePost(@RequestParam(value = "id")int id, Model model)
    {
        voieService.deleteById(id);
        model.addAttribute("liste", voieService.getListVoie());
        return "voie/liste";
    }

}
