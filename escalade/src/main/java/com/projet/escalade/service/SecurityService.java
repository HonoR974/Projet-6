package com.projet.escalade.service;


import com.projet.escalade.entity.User;

public interface SecurityService {


    boolean isAuthenticated();


    void autoLogin(String username, String password);

    String getNameUser();

    User getUser();


}