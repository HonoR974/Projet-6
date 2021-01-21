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



    @Override
    public List<Topo> getTopoListByIdUser(int id_user)
    {
        User user = userRepository.findById(id_user);
        List<Topo> topoList = new ArrayList<>();
        int i = 0;

        for (Topo topo : user.getTopos())
        {
                if (topo.isDisponible())
                {
                    topoList.add(topo);
                }
        }

        return topoList;
    }



    @Override
    public List<Topo> getTopoListByVisible()
    {
        return  topoRepository.findTopoByVisible(true);
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
        t.setDisponible(true);
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
        return t.getSites();

    }

    @Override
    public User getUserById(int id)
    {
        Topo t = topoRepository.findById(id);
        return  t.getUser();
    }

}
