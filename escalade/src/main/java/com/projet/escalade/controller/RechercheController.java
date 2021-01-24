package com.projet.escalade.controller;

import com.projet.escalade.entity.Site;
import com.projet.escalade.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RechercheController {

        @Autowired
        private SearchService searchService;


        //-----------Recherche Site -----//
        @GetMapping(value = "/search/topo")
        public String searchTopo(@RequestParam(value = "search")String s,
                                 Model model)
        {
                List<Site> siteList = searchService.findByNomOrRegion(s,s);

                if (siteList.isEmpty())
                {
                        return "search/404";
                }
                else
                {
                        model.addAttribute("liste", siteList);
                        return "search/result";
                }
        }
}
