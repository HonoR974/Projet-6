package com.projet.escalade.controller;

import com.projet.escalade.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RechercheController {

        @Autowired
        private SearchService searchService;


        //-----------Recherche Site -----//
        @GetMapping(value = "/search/topo")
        public String searchTopo(@RequestParam(value = "search")String s,
                                 Model model)
        {
                model.addAttribute("liste", searchService.findByNomOrRegion(s,s));
                return "search/result";
        }
}
