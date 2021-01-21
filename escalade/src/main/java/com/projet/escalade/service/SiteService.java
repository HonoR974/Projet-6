package com.projet.escalade.service;

import com.projet.escalade.entity.Commentaire;
import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Topo;
import com.projet.escalade.entity.Voie;

import java.util.List;

public interface SiteService {


    List<Site> getSiteListByIdTop(int id_topo);

    List<Site> getSiteListByVisible();

    List<Voie> getVoieBySite(int id);

    List<Commentaire> getCommentaireListByIdSite(int id);

    Site getSiteById(int id);

    Site createSite();

    Site saveSite(int id_topo, boolean visible, String r, String n, String a );

    Site updateSite(int id, boolean visible, String r, String n,String a);

    Topo getTopoByIdTopo(int id);

    Topo getTopoByIdSite(int id);

    int getIdTopoByIdSite (int id);

    void deleteById(int id);

    void tagSite(int id);






}
