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

/**
 * Controller des commentaires
 */
@Controller
public class CommentaireController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CommentaireService commentaireService;

    /**
     * Un utilisatuer connecté post un commentaire.
     * @param id id_site
     * @param contenu - contenu du commentaire
     * @param model model
     * @return intro/detailSite
     */
    @PostMapping(value = "/comment/detailSite")
    public String Comment(@RequestParam(value = "id")int id,
                                @RequestParam(value = "contenu")String contenu,
                                Model model)
    {
        //sauvegarde du commentaire
        commentaireService.saveComment(id, securityService.getNameUser(), contenu);

        // renvoie ce que la page detailSite a besoin

        model.addAttribute("site", siteService.getSiteByIdSite(id));
        model.addAttribute("liste", siteService.getVoieByIdSite(id));
        model.addAttribute("topo", siteService.getTopoByIdSite(id));
        model.addAttribute("commentaires", siteService.getCommentaireListByIdSite(id));
        model.addAttribute("comm", commentaireService.createComment());
        return "intro/detailSite";
    }


    /**
     * Un membre de l'associtation veut modifier un commentaire.
     * @param id id_commentaire
     * @param model model
     * @return membre/updateComment
     */
    @GetMapping(value = "/comment/update")
    public String getUpdateComment(@RequestParam(value = "id")int id,
                                Model model)
    {
        model.addAttribute("comment", commentaireService.getCommentById(id));

        return "membre/updateComment";
    }

    /**
     * Un membre de l'association modifie un commentaire
     * @param id id_commentaire
     * @param contenu contenu du nouveau commentaire
     * @param model model
     * @return intro/detailSite
     */
    @PostMapping(value = "/comment/update")
    public String PostUpdateComment(@RequestParam(value = "id")int id,
                                    @RequestParam(value = "contenu")String contenu,
                                    Model model)
    {
        //modifie le commentaire
        commentaireService.updateComment(id, contenu);

        //detail Site ( site, listeVoie, topo , commentaires & comm )

        model.addAttribute("site", siteService.getSiteByIdSite(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("liste", siteService.getVoieByIdSite(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("topo", siteService.getTopoByIdSite(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("commentaires", siteService.getCommentaireListByIdSite(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("comm", commentaireService.createComment());

        return "intro/detailSite";
    }

    /**
     * Un membre de l'association supprimer un commentaire
     * @param id id_commentaire
     * @param model model
     * @return intro/detailSite
     */
    @PostMapping(value = "/comment/delete")
    public String delete(@RequestParam(value = "id")int id,
                         Model model)
    {
        //supprimer le commentaire
        commentaireService.deleteComment(id);

        //detail Site ( site, listeVoie, topo , commentaires & comm )
        model.addAttribute("site", siteService.getSiteByIdSite(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("liste", siteService.getVoieByIdSite(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("topo", siteService.getTopoByIdSite(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("commentaires", siteService.getCommentaireListByIdSite(commentaireService.getIdSiteByIdComment(id)));
        model.addAttribute("comm", commentaireService.createComment());

        return "intro/detailSite";
    }
}
