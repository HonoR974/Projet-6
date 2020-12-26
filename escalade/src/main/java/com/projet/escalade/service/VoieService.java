package com.projet.escalade.service;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Voie;

import java.util.List;

public interface VoieService {

    Voie getVoieById(int id);

    Voie createVoie();

    Voie saveVoie(String n, String c);

    List<Voie> getListVoie();

    Voie updateVoie(int id, String n, String c);

    void deleteById(int id);

    Site backToSite();
}
