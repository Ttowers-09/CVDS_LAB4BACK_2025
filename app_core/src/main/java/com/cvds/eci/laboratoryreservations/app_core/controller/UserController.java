package com.cvds.eci.laboratoryreservations.app_core.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvds.eci.laboratoryreservations.app_core.model.User;
import com.cvds.eci.laboratoryreservations.app_core.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This Java function returns a list of Users as a ResponseEntity.
     * 
     * @return A list of Users objects is being returned in the ResponseEntity body.
     */
    @GetMapping
    public ResponseEntity<?> getUsers(){
        try{
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        }catch(RuntimeException e){
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e));
        }
        

    }

    /**
     * This Java function adds a User entity to the system and returns a response indicating
     * successful insertion.
     * 
     * @param User The `addUser` method in the code snippet is a POST mapping that adds a
     * new User to the system. The method takes a `User` object as a request body and then
     * calls the `addUser` method from the `labService` to add the User to the system
     * @return A ResponseEntity object with a status code of 201 (Created) and a body containing a Map
     * with a "response" key and the value "User Insert OK".
     */
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) { // @RequestBody Convierte automáticamente el JSON del cuerpo de la petición en un objeto Java.
        try{
            User saveUser = userService.addUser(user); // Guarda y obtiene el ID
            return ResponseEntity.status(201).body(saveUser);
        }catch(RuntimeException e){
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e));
        }
        

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        try{
            String userDelete = userService.deleteUser(id);
            return ResponseEntity.status(200).body(Collections.singletonMap("response", "User: " + userDelete  + " Delete OK"));
        }catch(RuntimeException e){
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e));
        }
        
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        try{
            User userSearch = userService.getUserById(id);
            return ResponseEntity.status(200).body(userSearch);
        }catch(RuntimeException e){
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e));
        }
        

    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name){
        try{
            User userSearch = userService.getUserByName(name);
            return ResponseEntity.status(200).body(userSearch);
        }catch(RuntimeException e){
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e));
        }
        

    }


    
}
