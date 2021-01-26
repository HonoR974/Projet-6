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

/**
 * TopoServiceImpl
 */
@Service
public class TopoServiceImpl  implements TopoService{


    @Autowired
    private TopoRepository topoRepository;

    @Autowired
    private UserRepository userRepository;


    /**
     * retunr une liste de topo par l'id d'un user
     * @param id_user id_user
     * @return liste de topo
     */
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


    /**
     * Return une liste de topo visible
     * @return liste de topo
     */
    @Override
    public List<Topo> getTopoListByVisible()
    {
        return  topoRepository.findTopoByVisible(true);
    }

    /**
     * Création d'un topo
     * @param id_user id_user
     * @return topo
     */
    @Override
    public Topo createTopo( int id_user)
    {
        Topo t = new Topo();
        User u = userRepository.findById(id_user);
        t.setUser(u);

        topoRepository.save(t);
        return  t ;
    }

    /**
     * Sauvegarde un topo
     * @param id id_topo
     * @param visible visible
     * @param nom nom
     * @param description description
     * @param date date
     * @return topo
     */
    @Override
    public Topo saveTopo(int id, boolean visible , String nom, String description, Date date)
    {
        Topo t = topoRepository.findById(id);
        t.setVisible(visible);
        t.setNom(nom);
        t.setDescription(description);
        t.setDate_creation(date);
        t.setDisponible(true);
        topoRepository.save(t);

        return t ;
    }


    /**
     * Return un topo par son id
     * @param id id_topo
     * @return topo
     */
    @Override
    public Topo getTopoByIdTopo(int id)
    {
        return topoRepository.findById(id);
    }

    /**
     * Modifie un topo
     * @param id id_topo
     * @param visible visible
     * @param nom nom
     * @param des description
     * @return topo
     */
    @Override
    public Topo updateTopo(int id, boolean visible , String nom , String des)
    {
        Topo t = topoRepository.findById(id);

        t.setVisible(visible);
        t.setNom(nom);
        t.setDescription(des);
        topoRepository.save(t);
        return t;
    }

    /**
     * Suppression d'un topo par son id
     * @param id id_topo
     */
    @Override
    public void deleteById(int id)
    {
        topoRepository.deleteById(id);
    }

    /**
     * Return les sites d'un topo
     * @param id id_topo
     * @return liste de site
     */
    @Override
    public List<Site> sendSiteByTopo(int id)
    {
        Topo t = topoRepository.findById(id);
        return t.getSites();

    }

    /**
     * Return un user par l'id d'un topo
     * @param id id_topo
     * @return user
     */
    @Override
    public User getUserByIdTopo(int id)
    {
        Topo t = topoRepository.findById(id);
        return  t.getUser();
    }

}
