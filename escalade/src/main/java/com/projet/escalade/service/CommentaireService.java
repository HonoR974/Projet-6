package com.projet.escalade.service;


import com.projet.escalade.entity.Commentaire;

/**
 * Interface CommentaireService
 */
public interface CommentaireService {

    /**
     * Creer un commentaire
     * @return le commentaire créé
     */
    Commentaire createComment();

    /**
     * Return un commentaire par son id
     * @param id id_commentaire
     * @return commentaire
     */
    Commentaire getCommentById(int id);

    /**
     * Return l'id d'un site par l'id d'un commentaire
     * @param id id_site
     * @return id_site
     */
    int getIdSiteByIdComment(int id);

    /**
     * Modifie le contenu du commentaire
     * @param id id_commentaire
     * @param contenu cotenu
     */
    void updateComment(int id, String contenu );

    /**
     * Supprime un commentaire
     * @param id id_commentaire
     */
    void deleteComment(int id);

    /**
     * Sauvegarde un commentaire d'un utilisateur sur un site
     * @param id_site id_site
     * @param name_user username
     * @param contenu contenu
     */
    void saveComment(int id_site, String name_user, String contenu);

}
