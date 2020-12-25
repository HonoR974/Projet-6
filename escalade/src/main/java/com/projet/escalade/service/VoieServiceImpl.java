package com.projet.escalade.service;

import com.projet.escalade.entity.Voie;
import com.projet.escalade.repository.VoieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoieServiceImpl implements VoieService{

    @Autowired
    private VoieRepository voieRepository;

    @Override
    public Voie getVoieById(int id)
    {
        Voie voie = voieRepository.findById(id);

        return voie;
    }

    @Override
    public Voie createVoie()
    {
        Voie v = new Voie();
        return v;
    }

    @Override
    public Voie saveVoie(String n, String c)
    {
        Voie v = new Voie();
        v.setNom(n);
        v.setCotation(c);
        voieRepository.save(v);
        return v;
    }

    @Override
    public List<Voie> getListVoie()
    {
        return voieRepository.findAll();
    }


    @Override
    public Voie updateVoie(int id, String n , String c)
    {
        Voie v = voieRepository.findById(id);
        v.setNom(n);
        v.setCotation(c);

        voieRepository.save(v);
        return v;
    }

    @Override
    public void deleteById(int id)
    {
        voieRepository.deleteById(id);
    }
}
