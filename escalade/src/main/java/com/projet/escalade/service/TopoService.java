package com.projet.escalade.service;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Topo;

import java.util.Date;
import java.util.List;

public interface TopoService {


     List<Topo> getTopoList();

     Topo createTopo();

     Topo saveTopo(boolean visible, String nom, String descritpion, Date dateCreation);

     Topo getTopoByID(int id);

     Topo updateTopo(int id, boolean v , String n, String d);

     void deleteById(int id);

     List<Site> sendSiteByTopo(int id);

}
