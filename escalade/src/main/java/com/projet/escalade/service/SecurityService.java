package com.projet.escalade.service;


import com.projet.escalade.entity.User;

/**
 * Interface SecurityService
 */
public interface SecurityService {

    /**
     * Return true si l'user est conecté
     * @return boolean
     */
    boolean isAuthenticated();

    /**
     * L'utilisateur se connecte
     * @param username usernae
     * @param password mdp
     */
    void autoLogin(String username, String password);


    /**
     * Return le nom de l'user connecté
     * @return name
     */
    String getNameUser();


    /**
     * Return l'user connecté
     * @return user
     */
    User getUser();


}