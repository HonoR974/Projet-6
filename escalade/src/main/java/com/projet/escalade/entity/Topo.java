package com.projet.escalade.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "topo")
public class Topo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name ="id")
    private int id;

    @Column(name = "visible")
    private boolean visible;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;


    @Column(name = "date_creation")
    private Date date_creation;


    @OneToMany(mappedBy = "topo")
    private List<Site> sites;

    @ManyToOne
    private User user;



    public Topo()
    {

    }

    public Topo(boolean v, String n, String d, Date da )
    {
        this.visible = v ;
        this.nom = n ;
        this.description = d;
        this.date_creation =da;
    }


    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }


    @Override
    public String toString() {
        return "Topo{" +
                "id=" + id +
                ", visible=" + visible +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", date_creation=" + date_creation +
                ", sites=" + sites +
                '}';
    }


}
