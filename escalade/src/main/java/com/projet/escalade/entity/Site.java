package com.projet.escalade.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "site")
public class Site {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name ="id")
    private int id;

    @Column(name = "region")
    private String region;

    @Column(name = "nom")
    private String nom;

    @Column(name = "adresse")
    private String adresse;

    @ManyToOne
    private Topo topo;

    @OneToMany(mappedBy = "site")
    private List<Voie> voies;






    public List<Voie> getVoies() {
        return voies;
    }

    public void setVoies(List<Voie> voies) {
        this.voies = voies;
    }

    public Topo getTopo() {
        return topo;
    }

    public void setTopo(Topo topo) {
        this.topo = topo;
    }

    public Site(){}

    public Site(String r, String n, String a )
    {
        this.region = r;
        this.nom = n;
        this.adresse = a;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}
