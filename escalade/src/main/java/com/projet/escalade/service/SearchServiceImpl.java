package com.projet.escalade.service;

import com.projet.escalade.entity.Site;
import com.projet.escalade.entity.Topo;
import com.projet.escalade.repository.SiteRepository;
import com.projet.escalade.repository.TopoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * SearchServiceImpl
 */
@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    private SiteRepository siteRepository;


    /**
     * Effectue une recherche de site  par le nom et la region
     * @param name nom
     * @param region region
     * @return liste de site
     */
    @Override
    public List<Site> findByNomOrRegion(String name, String region)
    {
        List<Site> sites = siteRepository.findByNomIgnoreCaseOrRegionIgnoreCase(name, region);


        List<Site> finalSite = new ArrayList<>();

        for (Site site : sites)
        {
            if (site.getTopo().isVisible())
            {
                finalSite.add(site);
            }
        }
        return finalSite;
    }
}
