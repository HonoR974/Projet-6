package com.projet.escalade.service;

import com.projet.escalade.entity.Site;

import java.util.List;

public interface SearchService {

    List<Site> findByNomOrRegion(String name, String region);
}
