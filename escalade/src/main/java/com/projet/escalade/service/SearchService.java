package com.projet.escalade.service;

import com.projet.escalade.entity.Site;

import java.util.List;

/**
 * Interface SearchService
 */
public interface SearchService {

    /**
     * Effectue une recherche de site  par le nom et la region
     * @param name nom
     * @param region region
     * @return liste de site
     */
    List<Site> findByNomOrRegion(String name, String region);
}
