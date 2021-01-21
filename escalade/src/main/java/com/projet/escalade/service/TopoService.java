package com.projet.escalade.service;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Topo;
import com.projet.escalade.entity.User;

import java.util.Date;
import java.util.List;

public interface TopoService {


     List<Topo> getTopoListByIdUser(int id_user);


     List<Topo> getTopoListByVisible( );

     Topo createTopo(int id_user);

     Topo saveTopo(int id, boolean visible, String nom, String descritpion, Date dateCreation);

     Topo getTopoByID(int id);

     Topo updateTopo(int id, boolean v , String n, String d);

     void deleteById(int id);

     List<Site> sendSiteByTopo(int id);

     User getUserById(int id);

}
