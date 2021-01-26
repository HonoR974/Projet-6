package com.projet.escalade.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity Commentaire
 */
@Entity
@Table(name = "commentaire")
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Site site;

    @ManyToOne
    private User user;

    @Column(name = "contenu")
    private String contenu;

    @Column(name = "date")
    private Date date;


    public Commentaire()
    {}

    /**
     * return id
     * @return commentaire.id
     */
    public int getId() {
        return id;
    }

    /**
     * set id_commentaire
     * @param id id_commentaire
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * return site
     * @return site
     */
    public Site getSite() {
        return site;
    }

    /**
     * set site
     * @param site site
     */
    public void setSite(Site site) {
        this.site = site;
    }

    /**
     * return user
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * set user
     * @param user user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * return contenu
     * @return contenu
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * set contenu
     * @param contenu contenu
     */
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    /**
     * return date
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * set date
     * @param date date
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
