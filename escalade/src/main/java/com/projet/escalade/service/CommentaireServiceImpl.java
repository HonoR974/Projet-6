package com.projet.escalade.service;

import com.projet.escalade.entity.Commentaire;
import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.User;
import com.projet.escalade.repository.CommentaireRepository;
import com.projet.escalade.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentaireServiceImpl implements CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private UserService userService;

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

    @Override
    public Commentaire createComment()
    {
        Commentaire c = new Commentaire();
        return c;
    }



}
