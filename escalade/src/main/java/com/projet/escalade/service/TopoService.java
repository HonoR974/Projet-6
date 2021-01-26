package com.projet.escalade.service;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Topo;
import com.projet.escalade.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Interface TopoService
 */
public interface TopoService {

     /**
      * retunr une liste de topo par l'id d'un user
      * @param id_user id_user
      * @return liste de topo
      */
     List<Topo> getTopoListByIdUser(int id_user);


     /**
      * Return une liste de topo visible
      * @return liste de topo
      */
     List<Topo> getTopoListByVisible( );

     /**
      * Création d'un topo
      * @param id_user id_user
      * @return topo
      */
     Topo createTopo(int id_user);

     /**
      * Sauvegarde un topo
      * @param id id_topo
      * @param visible visible
      * @param nom nom
      * @param description description
      * @param date date
      * @return topo
      */
     Topo saveTopo(int id, boolean visible, String nom, String description, Date date);

     /**
      * Return un topo par son id
      * @param id id_topo
      * @return topo
      */
     Topo getTopoByIdTopo(int id);

     /**
      * Modifie un topo
      * @param id id_topo
      * @param visible visible
      * @param nom nom
      * @param des description
      * @return topo
      */
     Topo updateTopo(int id, boolean visible , String nom, String des);

     /**
      * Suppression d'un topo par son id
      * @param id id_topo
      */
     void deleteById(int id);


     /**
      * Return les sites d'un topo
      * @param id id_topo
      * @return liste de site
      */
     List<Site> sendSiteByTopo(int id);


     /**
      * Return un user par l'id d'un topo
      * @param id id_topo
      * @return user
      */
     User getUserByIdTopo(int id);

}
