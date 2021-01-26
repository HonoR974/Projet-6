package com.projet.escalade.service;

import com.projet.escalade.entity.Commentaire;
import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Topo;
import com.projet.escalade.entity.Voie;

import java.util.List;

/**
 * Interface SiteService
 */
public interface SiteService {

    /**
     * Return une liste de site par l'id du topo
     * @param id_topo id_topo
     * @return liste de site
     */
    List<Site> getSiteListByIdTopo(int id_topo);

    /**
     * Return une liste de site
     * <p>Tout les sites qui sont visible </p>
     * @return liste de site
     */
    List<Site> getSiteListByVisible();

    /**
     * Return les voie appartenant au site
     * @param id id_site
     * @return liste de voie
     */
    List<Voie> getVoieByIdSite(int id);

    /**
     * Return un site par son id
     * @param id id_site
     * @return site
     */
    Site getSiteByIdSite(int id);


    /**
     * Creation d'un site
     * @return site
     */
    Site createSite();

    /**
     * Sauvegarde un site sur un topo
     * @param id_topo id_topo
     * @param visible visible
     * @param region region
     * @param nom nom
     * @param adr adresse
     * @return site
     */
    Site saveSite(int id_topo, boolean visible, String region, String nom, String adr );


    /**
     * Modifie un site
     * @param id id_site
     * @param visible visible
     * @param region region
     * @param nom nom
     * @param ad adresse
     * @return site
     */
    Site updateSite(int id, boolean visible, String r, String n,String a);


    /**
     * Return un topo par son id
     * @param id id_topo
     * @return
     */
    Topo getTopoByIdTopo(int id);


    /**
     * return un topo par l'id d'un site
     * @param id id_site
     * @return topo
     */
    Topo getTopoByIdSite(int id);

    /**
     * Return une liste de commentaire par l'id d'un site
     * @param id id_site
     * @return liste de commentaire
     */
    List<Commentaire> getCommentaireListByIdSite(int id);

    /**
     * Return l'id d'un topo par l'id d'un site
     * @param id id_site
     * @return id_topo
     */
    int getIdTopoByIdSite (int id);


    /**
     * Suppression d'un site par son id
     * @param id id_site
     */
    void deleteById(int id);


    /**
     * Un admin tague un site
     * @param id id_site
     */
    void tagSite(int id);






}
