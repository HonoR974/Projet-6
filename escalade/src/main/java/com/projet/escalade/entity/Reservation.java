package com.projet.escalade.entity;

import javax.persistence.*;

/**
 * Entity Reservation
 */
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String message;

    @ManyToOne
    private Statut statut;

    @ManyToOne
    private User user;

    @ManyToOne
    private Topo topo;



    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", statut=" + statut +
                ", user=" + user +
                ", topo=" + topo +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topo getTopo() {
        return topo;
    }

    public void setTopo(Topo topo) {
        this.topo = topo;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
