package com.projet.escalade.repository;

import com.projet.escalade.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepository  extends JpaRepository<Site,Integer> {

    Site findById(int id);


}
