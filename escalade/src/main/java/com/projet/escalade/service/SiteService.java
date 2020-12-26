package com.projet.escalade.service;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Topo;
import com.projet.escalade.entity.Voie;

import java.util.List;

public interface SiteService {


    List<Site> getSiteListByIdTop(int id_topo);

    List<Voie> getVoieBySite(int id);

    Site getSiteById(int id);

    Site createSite();

    Site saveSite(String r, String n, String a, int id_topo);

    Site updateSite(int id, String r, String n,String a);

    Topo getTopoById(int id);


    int getIdTopoByIdSite (int id);

    void deleteById(int id);






}
