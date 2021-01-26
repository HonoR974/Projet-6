package com.projet.escalade.controller;

import com.projet.escalade.entity.Site;
import com.projet.escalade.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller pour la recherche de site
 */
@Controller
public class RechercheController {

        @Autowired
        private SearchService searchService;


        /**
         * Une recherche est effectué
         * @param search le contenu de la recherche
         * @param model model
         * @return si la recherche abouti search/result, sinon search/404
         *
         */
        @GetMapping(value = "/search/topo")
        public String searchTopo(@RequestParam(value = "search")String search,
                                 Model model)
        {
                List<Site> siteList = searchService.findByNomOrRegion(search,search);

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
