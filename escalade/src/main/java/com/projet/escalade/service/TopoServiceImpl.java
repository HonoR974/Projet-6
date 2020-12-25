package com.projet.escalade.service;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Topo;
import com.projet.escalade.repository.TopoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TopoServiceImpl  implements TopoService{


    @Autowired
    private TopoRepository topoRepository;

    private List<Topo> topoList;

    @Override
    public List<Topo> getTopoList()
    {
        topoList = topoRepository.findAll();
        return topoList;
    }

    @Override
    public Topo createTopo( )
    {
        Topo t = new Topo();
        return  t ;
    }

    @Override
    public Topo saveTopo(boolean v, String n, String des, Date date)
    {
        Topo t = new Topo();
        t.setVisible(v);
        t.setNom(n);
        t.setDescription(des);
        t.setDate_creation(date);
        topoRepository.save(t);

        return t ;
    }


    @Override
    public Topo getTopoByID(int id)
    {
        return topoRepository.findById(id);
    }

    @Override
    public Topo updateTopo(int id, boolean v, String n, String d)
    {
        Topo t = topoRepository.findById(id);

        t.setVisible(v);
        t.setNom(n);
        t.setDescription(d);
        topoRepository.save(t);
        return t;
    }

    @Override
    public void deleteById(int id)
    {
        topoRepository.deleteById(id);
    }

    @Override
    public List<Site> sendSiteByTopo(int id)
    {
        Topo t = topoRepository.findById(id);

        List<Site> sites = t.getSites();
        return sites;

    }


}
