package com.projet.escalade.service;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Voie;

import java.util.List;

/**
 * Interface VoieService
 */
public interface VoieService {



    /**
     * return une voie par son id
     * @param id id_voie
     * @return voie
     */
    Voie getVoieByIdVoie(int id);


    /**
     * Return une liste de voie par l'id d'un iste
     * @param id_site id_site
     * @return liste de voie
     */
    List<Voie> getListVoieByIdSite(int id_site);


    /**
     * Return l'id d'un site par l'id d'une voie
     * @param id id_voie
     * @return id_site
     */
    int getIdSiteByIdVoie(int id);


    /**
     * Créatio d'une voie
     * @param id_site id_site
     * @return voie
     */
    Voie createVoie(int id_site );

    /**
     * Sauevgarde une voie
     * @param nom nom
     * @param cot cotation
     * @param id_site id_site
     * @return voie
     */
    Voie saveVoie(String nom, String cot, int id_site);

    /**
     * Modifie une voie
     * @param id id_voie
     * @param nom nom
     * @param cot cotation
     * @return voie
     */
    Voie updateVoie(int id, String nom, String cot);

    /**
     * Suppression d'une voie par son id
     * @param id id_voie
     */
    void deleteById(int id);

    /**
     * Return un iste par l'id du site
     * @param id id_site
     * @return site
     */
    Site getSiteByIdSite(int id);

    /**
     * Return un site par sa voie
     * @param id id_voie
     * @return site
     */
    Site getSiteByIdVoie(int id);




}
