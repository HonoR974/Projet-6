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

/**
 * SiteServiceImpl
 */
@Service
public class SiteServiceImpl  implements  SiteService{


    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private TopoRepository topoRepository;


    /**
     * Return une liste de site par l'id du topo
     * @param id_topo id_topo
     * @return liste de site
     */
    @Override
    public List<Site> getSiteListByIdTopo(int id_topo)
    {
        Topo t = topoRepository.findById(id_topo);

        return  siteRepository.findByTopo(t);
    }

    /**
     * Return une liste de site
     * <p>Tout les sites qui sont visible </p>
     * @return liste de site
     */
    @Override
    public List<Site> getSiteListByVisible()
    {
           return siteRepository.findByVisible(true);
    }

    /**
     * Return les voie appartenant au site
     * @param id id_site
     * @return liste de voie
     */
    @Override
    public List<Voie> getVoieByIdSite(int id)
    {
        Site s = siteRepository.findById(id);

        return s.getVoies();
    }


    /**
     * Return un site par son id
     * @param id id_site
     * @return site
     */
    @Override
    public Site getSiteByIdSite(int id)
    {
        return siteRepository.findById(id);
    }

    /**
     * Creation d'un site
     * @return site
     */
    @Override
    public Site createSite( )
    {

        return new Site();
    }


    /**
     * Sauvegarde un site sur un topo
     * @param id_topo id_topo
     * @param visible visible
     * @param region region
     * @param nom nom
     * @param adr adresse
     * @return site
     */
    @Override
    public Site saveSite(int id_topo, boolean visible, String region, String nom, String adr)
    {
        Site s = new Site(region,nom,adr);

        Topo t = topoRepository.findById(id_topo);
        s.setVisible(visible);
        s.setTopo(t);
        s.setTag(false);

        siteRepository.save(s);
        return s;
    }

    /**
     * Modifie un site
     * @param id id_site
     * @param visible visible
     * @param region region
     * @param nom nom
     * @param ad adresse
     * @return site
     */
    @Override
    public Site updateSite(int id, boolean visible, String region , String nom, String ad)
    {
        Site s = siteRepository.findById(id);
        s.setVisible(visible);
        s.setRegion(region);
        s.setNom(nom);
        s.setAdresse(ad);

        siteRepository.save(s);

        return s;
    }

    /**
     * Return un topo par son id
     * @param id id_topo
     * @return
     */
    @Override
    public Topo getTopoByIdTopo(int id)
    {

        return  topoRepository.findById(id);
    }


    /**
     * return un topo par l'id d'un site
     * @param id id_site
     * @return topo
     */
    @Override
    public Topo getTopoByIdSite(int id)
    {
        Site s = siteRepository.findById(id);
        return s.getTopo();
    }

    /**
     * Return une liste de commentaire par l'id d'un site
     * @param id id_site
     * @return liste de commentaire
     */
    @Override
    public  List<Commentaire> getCommentaireListByIdSite(int id)
    {
        Site s = siteRepository.findById(id);
        return s.getCommentaires();
    }


    /**
     * Suppression d'un site par son id
     * @param id id_site
     */
    @Override
    public void deleteById(int id)
    {

        siteRepository.deleteById(id);
    }


    /**
     * Return l'id d'un topo par l'id d'un site
     * @param id id_site
     * @return id_topo
     */
    @Override
    public int getIdTopoByIdSite(int id)
    {
        Site s = siteRepository.findById(id);
        Topo t = s.getTopo();

        return t.getId();
    }


    /**
     * Un admin tague un site
     * @param id id_site
     */
    @Override
    public void tagSite(int id)
    {
        Site s = siteRepository.findById(id);
        s.setTag(true);
        siteRepository.save(s);
    }
}
