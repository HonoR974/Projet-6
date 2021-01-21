package com.projet.escalade.service;



import com.projet.escalade.entity.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    int getIdUser(String username);

    User getUserById(int id);

    boolean topoIsMine(String name_user, int id_topo);



}
