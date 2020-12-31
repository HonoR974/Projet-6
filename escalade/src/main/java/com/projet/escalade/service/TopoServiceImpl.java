package com.projet.escalade.service;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Topo;
import com.projet.escalade.entity.User;
import com.projet.escalade.repository.TopoRepository;
import com.projet.escalade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TopoServiceImpl  implements TopoService{


    @Autowired
    private TopoRepository topoRepository;

    @Autowired
    private UserRepository userRepository;

    private List<Topo> topoList;

    @Override
    public List<Topo> getTopoListByIdUser(int id_user)
    {
        User u = userRepository.findById(id_user);

        topoList = topoRepository.findByUser(u);
        return topoList;
    }

    @Override
    public List<Topo> getTopoListByVisible()
    {
        List<Topo> topos =  topoRepository.findTopoByVisible(true);
        return topos;
    }

    @Override
    public Topo createTopo( int id_user)
    {
        Topo t = new Topo();
        User u = userRepository.findById(id_user);
        t.setUser(u);
        topoRepository.save(t);
        return  t ;
    }

    @Override
    public Topo saveTopo(int id, boolean v, String n, String des, Date date)
    {
        Topo t = topoRepository.findById(id);
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

    @Override
    public User getUserById(int id)
    {
        Topo t = topoRepository.findById(id);
        User u = t.getUser();
        return u;
    }

}
