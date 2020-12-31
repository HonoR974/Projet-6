package com.projet.escalade.service;


import com.projet.escalade.entity.Commentaire;

public interface CommentaireService {

    void saveComment(int id_site, String name_user, String contenu);

    Commentaire createComment();

}
