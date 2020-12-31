package com.projet.escalade.service;


import com.projet.escalade.entity.User;
import com.projet.escalade.repository.RoleRepository;
import com.projet.escalade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }



    @Override
    public int getIdUser(String username)
    {
        User u = userRepository.findByUsername(username);
        int id = u.getId();
        return id;
    }

    @Override
    public User getUserById(int id)
    {
        return userRepository.findById(id);
    }
}