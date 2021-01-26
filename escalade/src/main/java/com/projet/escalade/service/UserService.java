package com.projet.escalade.service;


/**
 * Interface UserService
 */

import com.projet.escalade.entity.User;

public interface UserService {

    /**
     * Un user s'inscrit
     * @param user user
     */
    void save(User user);

    /**
     * Trouve l'user par son pseudo
     * @param username username
     * @return user
     */
    User findByUsername(String username);

    /**
     * Return l'id d'un user par son pseudo
     * @param username username
     * @return id_user
     */
    int getIdUser(String username);

    /**
     * Return un user par son id
     * @param id id_user
     * @return user
     */
    User getUserById(int id);

    /**
     * Verfication si l'user consulte son topo
     * @param name_user username
     * @param id_topo id_topo
     * @return boolean
     */
    boolean topoIsMine(String name_user, int id_topo);



}
