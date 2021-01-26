package com.projet.escalade.repository;

import com.projet.escalade.entity.Commentaire;
import com.projet.escalade.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository des commentaires
 */
@Repository
public interface CommentaireRepository  extends JpaRepository<Commentaire, Integer> {


    /**
     * Trouve un commentaire par son id
     * @param id id_commentaire
     * @return commentaire
     */
    Commentaire findById(int id);

}
