package com.projet.escalade.service;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Topo;
import com.projet.escalade.entity.Voie;
import com.projet.escalade.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl  implements  SiteService{


    @Autowired
    private SiteRepository siteRepository;

    @Override
    public List<Site> getSiteList()
    {
        List<Site> sites = siteRepository.findAll();
        return sites;
    }

    @Override
    public Site getSiteById(int id)
    {
        return siteRepository.findById(id);
    }


    @Override
    public Site createSite()
    {
        Site s = new Site();
        return s;
    }

    @Override
    public Site saveSite(String r, String n, String a)
    {
        Site s = new Site(r,n,a);
        siteRepository.save(s);
        return s;
    }

    @Override
    public Site updateSite(int id, String r, String n, String a)
    {
        Site s = siteRepository.findById(id);
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
    public Topo backToTopo()
    {
       int id = 1 ;
        Site s = siteRepository.findById(id);

        return s.getTopo();
    }

    @Override
    public List<Voie> getVoieBySite(int id)
    {
        Site s = siteRepository.findById(id);

        List<Voie> voies = s.getVoies();

        return voies;
    }
}
