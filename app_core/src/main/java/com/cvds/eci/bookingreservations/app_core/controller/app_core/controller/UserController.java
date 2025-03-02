package com.cvds.eci.bookingreservations.app_core.controller.app_core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvds.eci.bookingreservations.app_core.controller.app_core.model.User;
import com.cvds.eci.bookingreservations.app_core.controller.app_core.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
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
    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        List<User> Users = userService.getAllUsers();
        return ResponseEntity.ok(Users);

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
    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody User user) { // @RequestBody Convierte automáticamente el JSON del cuerpo de la petición en un objeto Java.
        userService.addUser(user); // Guarda y obtiene el ID

        Map<String, String> response = new HashMap<String, String>();
        response.put("response", "User Insert OK");
        return ResponseEntity.status(201).body(response);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        userService.deletUser(id);
        Map<String, String> response = new HashMap<String, String>();
        response.put("response", "User: " + id  + " Deleted OK");
        return ResponseEntity.ok(response);
    }

    
    
}
