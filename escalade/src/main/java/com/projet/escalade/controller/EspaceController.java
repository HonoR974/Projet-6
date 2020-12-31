package com.projet.escalade.controller;

import com.projet.escalade.service.SecurityService;
import com.projet.escalade.service.TopoService;
import com.projet.escalade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EspaceController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TopoService topoService;

    @Autowired
    private UserService userService;


    //------------- Espace -----------//
    @GetMapping("/espace/accueil")
    public String espaceAccueil(Model model)
    {
        model.addAttribute("user", userService.findByUsername(securityService.getNameUser()));

        model.addAttribute("liste", topoService.getTopoListByIdUser(userService.getIdUser(securityService.getNameUser())));

        return "espace/accueil";
    }


}
