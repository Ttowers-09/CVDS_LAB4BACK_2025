package com.cvds.eci.bookingreservations.app_core.controller.app_core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvds.eci.bookingreservations.app_core.controller.app_core.model.User;
import com.cvds.eci.bookingreservations.app_core.controller.app_core.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }


    public void deletUser(String idUser){
        userRepository.deleteById(idUser);
    }


}
