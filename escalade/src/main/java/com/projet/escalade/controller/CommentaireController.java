package com.projet.escalade.controller;

import com.projet.escalade.entity.Commentaire;
import com.projet.escalade.service.CommentaireService;
import com.projet.escalade.service.SecurityService;
import com.projet.escalade.service.SiteService;
import com.projet.escalade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentaireController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CommentaireService commentaireService;

    @PostMapping(value = "/comment/detailSite")
    public String postComment(@RequestParam(value = "id")int id,
                                @RequestParam(value = "contenu")String c,
                                Model model)
    {
        commentaireService.saveComment(id, securityService.getNameUser(), c);

        //on renvoie ce que lage a besoin
        model.addAttribute("site", siteService.getSiteById(id));
        model.addAttribute("liste", siteService.getVoieBySite(id));
        model.addAttribute("topo", siteService.getTopoByIdSite(id));
        model.addAttribute("commentaires", siteService.getCommentaireListByIdSite(id));
        model.addAttribute("comm", commentaireService.createComment());
        return "intro/detailSite";
    }

}
