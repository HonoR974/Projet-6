package com.projet.escalade.service;

import com.projet.escalade.entity.Commentaire;
import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Topo;
import com.projet.escalade.entity.Voie;
import com.projet.escalade.repository.SiteRepository;
import com.projet.escalade.repository.TopoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl  implements  SiteService{


    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private TopoRepository topoRepository;



    @Override
    public List<Site> getSiteListByIdTop(int id_topo)
    {
        Topo t = topoRepository.findById(id_topo);

        return  siteRepository.findByTopo(t);
    }

    @Override
    public List<Site> getSiteListByVisible()
    {
           return siteRepository.findByVisible(true);
    }

    @Override
    public List<Voie> getVoieBySite(int id)
    {
        Site s = siteRepository.findById(id);

        return s.getVoies();
    }


    @Override
    public  List<Commentaire> getCommentaireListByIdSite(int id)
    {
        Site s = siteRepository.findById(id);
        return s.getCommentaires();
    }

    @Override
    public Site getSiteById(int id)
    {
        return siteRepository.findById(id);
    }


    @Override
    public Site createSite( )
    {
        Site s = new Site();
        return s;
    }

    //en fournissant l'id du topo
    @Override
    public Topo getTopoByIdTopo(int id)
    {
        Topo t = topoRepository.findById(id);

        return t;
    }

    @Override
    public Site saveSite(int id_topo, boolean visible, String r, String n, String a)
    {
        Site s = new Site(r,n,a);

        Topo t = topoRepository.findById(id_topo);
        s.setVisible(visible);
        s.setTopo(t);
        s.setTag(false);

        siteRepository.save(s);
        return s;
    }

    @Override
    public Site updateSite(int id, boolean visible, String r, String n, String a)
    {
        Site s = siteRepository.findById(id);
        s.setVisible(visible);
        s.setRegion(r);
        s.setNom(n);
        s.setAdresse(a);

        siteRepository.save(s);

        return s;
    }


    @Override
    public void deleteById(int id)
    {

        siteRepository.deleteById(id);
    }



    @Override
    public int getIdTopoByIdSite(int id)
    {
        Site s = siteRepository.findById(id);

        Topo t = s.getTopo();

        int id_topo = t.getId();

        return id_topo;
    }

    @Override
    public Topo getTopoByIdSite(int id)
    {
        Site s = siteRepository.findById(id);
        Topo t = s.getTopo();
        return t;
    }

    @Override
    public void tagSite(int id)
    {
        Site s = siteRepository.findById(id);
        s.setTag(true);
        siteRepository.save(s);
    }
}
