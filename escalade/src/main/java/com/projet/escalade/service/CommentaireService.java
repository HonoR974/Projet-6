package com.projet.escalade.service;


import com.projet.escalade.entity.Commentaire;

public interface CommentaireService {

    void saveComment(int id_site, String name_user, String contenu);

    Commentaire createComment();

    Commentaire getCommentById(int id);

    void updateComment(int id, String c);

    int getIdSiteByIdComment(int id);

    void deleteComment(int id);


}
