package com.projet.escalade.controller;

import com.projet.escalade.entity.Commentaire;
import com.projet.escalade.service.CommentaireService;
import com.projet.escalade.service.SecurityService;
import com.projet.escalade.service.SiteService;
import com.projet.escalade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentaireController {

    @Autowired
    private SiteService siteService;


    @Autowired
    private SecurityService securityService;

    @Autowired
    private CommentaireService commentaireService;

    @PostMapping(value = "/comment/detailSite")
    public String Comment(@RequestParam(value = "id")int id,
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


    @GetMapping(value = "/comment/update")
    public String getUpdateComment(@RequestParam(value = "id")int id,
                                Model model)
    {
        model.addAttribute("comment", commentaireService.getCommentById(id));

        return "membre/updateComment";
    }

    @PostMapping(value = "/comment/update")
    public String PostUpdateComment(@RequestParam(value = "id")int id,
                                    @RequestParam(value = "contenu")String c,
                                    Model model)
    {
        commentaireService.updateComment(id, c);
        //detail Site ( ite, listeVoie, topo , commentaires , et comm )


        model.addAttribute("site", siteService.getSiteById(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("liste", siteService.getVoieBySite(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("topo", siteService.getTopoByIdSite(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("commentaires", siteService.getCommentaireListByIdSite(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("comm", commentaireService.createComment());

        return "intro/detailSite";
    }

    @PostMapping(value = "/comment/delete")
    public String delete(@RequestParam(value = "id")int id,
                         Model model)
    {

        model.addAttribute("site", siteService.getSiteById(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("liste", siteService.getVoieBySite(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("topo", siteService.getTopoByIdSite(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("commentaires", siteService.getCommentaireListByIdSite(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("comm", commentaireService.createComment());

        commentaireService.deleteComment(id);
        return "intro/detailSite";
    }
}
