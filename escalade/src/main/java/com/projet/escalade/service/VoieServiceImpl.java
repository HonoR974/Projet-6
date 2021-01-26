package com.projet.escalade.service;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Voie;
import com.projet.escalade.repository.SiteRepository;
import com.projet.escalade.repository.VoieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * VoieServiceImpl
 */
@Service
public class VoieServiceImpl implements VoieService{

    @Autowired
    private VoieRepository voieRepository;

    @Autowired
    private SiteRepository siteRepository;

    private List<Voie> voies;

    /**
     * return une voie par son id
     * @param id id_voie
     * @return voie
     */
    @Override
    public Voie getVoieByIdVoie(int id)
    {
        return voieRepository.findById(id);
    }


    /**
     * Sauevgarde une voie
     * @param nom nom
     * @param cot cotation
     * @param id_site id_site
     * @return voie
     */
    @Override
    public Voie saveVoie(String nom, String cot,int id_site)
    {
        Site s = siteRepository.findById(id_site);
        Voie v = new Voie();
        v.setNom(nom);
        v.setCotation(cot);
        v.setSite(s);
        voieRepository.save(v);
        return v;
    }

    /**
     * Return une liste de voie par l'id d'un iste
     * @param id_site id_site
     * @return liste de voie
     */
    @Override
    public List<Voie> getListVoieByIdSite(int id_site)
    {
        Site s = siteRepository.findById(id_site);
         voies = voieRepository.findBySite(s);

        //return voieRepository.findBySite(s);
        return voies;
    }


    /**
     * Modifie une voie
     * @param id id_voie
     * @param nom nom
     * @param cot cotation
     * @return voie
     */
    @Override
    public Voie updateVoie(int id, String nom , String cot)
    {
        Voie v = voieRepository.findById(id);
        v.setNom(nom);
        v.setCotation(cot);

        System.out.println("\n \n " + v.toString() + "\n \n ");

        voieRepository.save(v);
        return v;
    }

    /**
     * Créatio d'une voie
     * @param id_site id_site
     * @return voie
     */
    @Override
   public Voie createVoie(int id_site )
    {
        Voie v = new Voie();
        v.setSite(siteRepository.findById(id_site));
        return v;
    }

    /**
     * Suppression d'une voie par son id
     * @param id id_voie
     */
    @Override
    public void deleteById(int id)
    {
        voieRepository.deleteById(id);
    }


    /**
     * Return un site par sa voie
     * @param id id_voie
     * @return site
     */
    @Override
    public Site getSiteByIdVoie(int id)
    {
        Voie v = voieRepository.findById(id);
        return v.getSite();
    }


    /**
     * Return un iste par l'id du site
     * @param id id_site
     * @return site
     */
   public  Site getSiteByIdSite(int id)
   {
       return  siteRepository.findById(id);

   }


    /**
     * Return l'id d'un site par l'id d'une voie
     * @param id id_voie
     * @return id_site
     */
   @Override
    public int  getIdSiteByIdVoie(int id)
   {
       Voie v = voieRepository.findById(id);
       Site s = v.getSite();
       return s.getId();
   }
}
