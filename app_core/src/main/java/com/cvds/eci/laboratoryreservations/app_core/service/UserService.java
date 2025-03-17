package com.cvds.eci.laboratoryreservations.app_core.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cvds.eci.laboratoryreservations.app_core.model.User;
import com.cvds.eci.laboratoryreservations.app_core.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); 

    public List<User> getAllUsers() {
        List<User> list = userRepository.findAll();
        if (list.isEmpty()){
            throw new RuntimeException("The user's list is empty");
        }
        return list;
    }
    
    public User getUserById(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            throw new RuntimeException("The user with the id: " + id + " cannot be found");
        }
        return user;
    }

    public User getUserByName(String name){
        User user = userRepository.findByName(name);
        if (user == null){
            throw new RuntimeException("The user  " + name + " cannot be found" );
        }
        return user;
    }

    public User addUser(User user) {
        User existingUser = userRepository.findByName(user.getName());
        if (existingUser != null){
            throw new RuntimeException("User " + user.getName() + " exists already.");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public String deleteUser(String id){
        User userSearch = userRepository.findById(id).orElse(null);
        if(userSearch == null){
            throw new RuntimeException("The user does not exist");
        }
        userRepository.deleteById(id);
        return id;
    }

    public String verify(User user) {
        Authentication authentication = 
        authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));

       if(authentication.isAuthenticated()){
        return jwtService.generateToken(user.getName());
       }

       return "fail";
    }

}
