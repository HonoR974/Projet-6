package com.projet.escalade.repository;

import com.projet.escalade.entity.Commentaire;
import com.projet.escalade.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository  extends JpaRepository<Commentaire, Integer> {

    List<Commentaire> findBySite(Site site);

}
