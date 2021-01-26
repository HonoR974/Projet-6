package com.projet.escalade.service;


import com.projet.escalade.entity.Role;
import com.projet.escalade.entity.Topo;
import com.projet.escalade.entity.User;
import com.projet.escalade.repository.RoleRepository;
import com.projet.escalade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Un user s'inscrit
     * @param user user
     */
    @Override
    public void save(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);
        user.getRoles().add(role);


        userRepository.save(user);
    }

    /**
     * Trouve l'user par son pseudo
     * @param username username
     * @return user
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    /**
     * Return l'id d'un user par son pseudo
     * @param username username
     * @return id_user
     */
    @Override
    public int getIdUser(String username)
    {
        User u = userRepository.findByUsername(username);
        return u.getId();
    }

    /**
     * Return un user par son id
     * @param id id_user
     * @return user
     */
    @Override
    public User getUserById(int id)
    {
        return userRepository.findById(id);
    }

    /**
     * Verfication si l'user consulte son topo
     * @param name_user username
     * @param id_topo id_topo
     * @return boolean
     */
    @Override
    public boolean topoIsMine (String name_user, int id_topo)
    {
        boolean isMine = false;
        User user = userRepository.findByUsername(name_user);

        if (user != null)
        {

        List<Topo> topos = user.getTopos();


        for (Topo topo : topos)
        {
            if (topo.getId() == id_topo) {
                isMine = true;
                break;
            }
        }
        }
        return isMine;
    }
}