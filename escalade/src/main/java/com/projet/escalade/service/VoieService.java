package com.projet.escalade.service;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Voie;

import java.util.List;

public interface VoieService {

    // voie
    Voie getVoieById(int id);



    List<Voie> getListVoieByIdSite(int id_site);

    int getIdSiteByIdVoie(int id);


    Voie createVoie(int id_site );

    Voie saveVoie(String n, String c, int id_site);

    Voie updateVoie(int id, String n, String c);

    void deleteById(int id);

    // site
    Site getSiteByIdSite(int id);

    Site getSiteByIdVoie(int id);




}
