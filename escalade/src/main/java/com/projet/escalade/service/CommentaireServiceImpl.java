package com.projet.escalade.service;

import com.projet.escalade.entity.Commentaire;
import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.User;
import com.projet.escalade.repository.CommentaireRepository;
import com.projet.escalade.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CommentaireServiceImpl
 */
@Service
public class CommentaireServiceImpl implements CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private UserService userService;

    /**
     * Creer un commentaire
     * @return le commentaire crée
     */
    @Override
    public Commentaire createComment()
    {
        return new Commentaire();
    }

    /**
     * Return un commentaire par son id
     * @param id id_commentaire
     * @return commentaire
     */
    @Override
    public Commentaire getCommentById(int id)
    {
        return commentaireRepository.findById(id);
    }

    /**
     * Return l'id d'un site par l'id d'un commentaire
     * @param id id_site
     * @return id_site
     */
    @Override
    public int getIdSiteByIdComment(int id)
    {
        Commentaire c = commentaireRepository.findById(id);
        Site s = c.getSite();
        return s.getId();
    }


    /**
     * Modifie le contenu du commentaire
     * @param id id_commentaire
     * @param contenu cotenu
     */
    @Override
    public void updateComment(int id, String contenu)
    {
        Commentaire c = commentaireRepository.findById(id);
        c.setContenu(contenu);
        commentaireRepository.save(c);

    }

    /**
     * Supprime un commentaire
     * @param id id_commentaire
     */
    @Override
    public void deleteComment(int id)
    {
        Commentaire c = commentaireRepository.findById(id);
        commentaireRepository.delete(c);

    }


    /**
     * Sauvegarde un commentaire d'un utilisateur sur un site
     * @param id_site id_site
     * @param name_user username
     * @param contenu contenu
     */
    @Override
    public void saveComment(int id_site, String  name_user, String contenu)
    {
        User u = userService.findByUsername(name_user);
        Site s = siteRepository.findById(id_site);

        Commentaire c = new Commentaire();

        c.setContenu(contenu);
        c.setSite(s);
        c.setUser(u);
        commentaireRepository.save(c);

        List<Commentaire> commentaires = s.getCommentaires();
        commentaires.add(c);
        s.setCommentaires(commentaires);
        siteRepository.save(s);

    }


}
