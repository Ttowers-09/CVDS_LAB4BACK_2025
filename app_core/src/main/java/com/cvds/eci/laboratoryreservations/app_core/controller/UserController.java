package com.cvds.eci.laboratoryreservations.app_core.controller;

import java.util.Collections;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:300")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Obtiene la lista de todos los usuarios registrados.
     *
     * @return ResponseEntity con la lista de usuarios en caso de éxito o un mensaje de error en caso de fallo.
     */
    @GetMapping("/get-users")
    public ResponseEntity<?> getUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e));
        }
    }

    /**
     * Agrega un nuevo usuario al sistema.
     *
     * @param user Objeto User que se desea agregar, recibido en el cuerpo de la solicitud.
     * @return ResponseEntity con el usuario guardado o un mensaje de error en caso de fallo.
     */
    @PostMapping("/add/user")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            User saveUser = userService.addUser(user);
            return ResponseEntity.status(201).body(saveUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    /**
     * Elimina un usuario específico por su identificador.
     *
     * @param id Identificador único del usuario que se desea eliminar.
     * @return ResponseEntity con un mensaje de confirmación o un mensaje de error en caso de fallo.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            String userDelete = userService.deleteUser(id);
            return ResponseEntity.status(200).body(Collections.singletonMap("response", "User: " + userDelete + " Delete OK"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e));
        }
    }

    /**
     * Obtiene un usuario por su identificador único.
     *
     * @param id Identificador único del usuario.
     * @return ResponseEntity con el usuario encontrado o un mensaje de error en caso de fallo.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        try {
            User userSearch = userService.getUserById(id);
            return ResponseEntity.status(200).body(userSearch);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    /**
     * Obtiene un usuario por su nombre.
     *
     * @param name Nombre del usuario a buscar.
     * @return ResponseEntity con el usuario encontrado o un mensaje de error en caso de fallo.
     */
    @GetMapping("/get/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name) {
        try {
            User userSearch = userService.getUserByName(name);
            return ResponseEntity.status(200).body(userSearch);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    /**
     * Verifica las credenciales de un usuario en el sistema.
     *
     * @param user Objeto User con la información de inicio de sesión.
     * @return String con la respuesta de verificación del usuario.
     */
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.verify(user);
    }
}
