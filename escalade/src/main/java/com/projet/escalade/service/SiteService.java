package com.projet.escalade.service;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Topo;

import java.util.List;

public interface SiteService {

    List<Site> getSiteList();

    Site getSiteById(int id);

    Site createSite();

    Site saveSite(String r, String n, String a);

    Site updateSite(int id, String r, String n,String a);

    void deleteById(int id);

    Topo backToTopo();


}
