package com.projet.escalade.service;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Voie;
import com.projet.escalade.repository.SiteRepository;
import com.projet.escalade.repository.VoieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoieServiceImpl implements VoieService{

    @Autowired
    private VoieRepository voieRepository;

    @Autowired
    private SiteRepository siteRepository;

    private List<Voie> voies;

    @Override
    public Voie getVoieById(int id)
    {
        Voie voie = voieRepository.findById(id);

        return voie;
    }


    @Override
    public Voie saveVoie(String n, String c,int id_site)
    {
        Site s = siteRepository.findById(id_site);
        Voie v = new Voie();
        v.setNom(n);
        v.setCotation(c);
        v.setSite(s);
        voieRepository.save(v);
        return v;
    }

    @Override
    public List<Voie> getListVoieByIdSite(int id_site)
    {
        Site s = siteRepository.findById(id_site);
         voies = voieRepository.findBySite(s);

        //return voieRepository.findBySite(s);
        return voies;
    }


    @Override
    public Voie updateVoie(int id, String n , String c)
    {
        Voie v = voieRepository.findById(id);
        v.setNom(n);
        v.setCotation(c);

        System.out.println("\n \n " + v.toString() + "\n \n ");

        voieRepository.save(v);
        return v;
    }
    @Override
   public Voie createVoie(int id_site )
    {
        Voie v = new Voie();
        v.setSite(siteRepository.findById(id_site));
        return v;
    }

    @Override
    public void deleteById(int id)
    {
        voieRepository.deleteById(id);
    }


    @Override
    public Site getSiteByIdVoie(int id)
    {
        Voie v = voieRepository.findById(id);
        Site s = v.getSite();
        return s;
    }


   public  Site getSiteByIdSite(int id)
   {
       return  siteRepository.findById(id);

   }


   @Override
    public int  getIdSiteByIdVoie(int id)
   {
       Voie v = voieRepository.findById(id);
       Site s = v.getSite();
       int a = s.getId();
       return a;
   }
}
