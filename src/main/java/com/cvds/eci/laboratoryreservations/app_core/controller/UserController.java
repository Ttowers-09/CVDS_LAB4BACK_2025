package com.cvds.eci.laboratoryreservations.app_core.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.PutMapping;


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
    @GetMapping
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
     * Actualiza la información de un usuario existente.
     *
     * @param id Identificador único del usuario a actualizar.
     * @param user Objeto User con la nueva información del usuario.
     * @return ResponseEntity con un mensaje de confirmación o un mensaje de error en caso de fallo.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable String id, @RequestBody User user) {
        try {
            userService.updateUser(id, user);
            return ResponseEntity.ok().body("User updated OK");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    /**
     * Método para autenticar a un usuario y generar un token de acceso.
     *
     * @param loginRequest Objeto User que contiene las credenciales de inicio de sesión.
     * @return ResponseEntity con el token de autenticación si las credenciales son válidas,
     *         o un estado HTTP 401 (No Autorizado) si la autenticación falla.
     *         En caso de error interno, retorna un estado HTTP 500 con un mensaje de error.
     */

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        try {
            // Este método se encarga de verificar usuario y rol automáticamente
            String token = userService.verifyUserCredentials(loginRequest);
            if (token != null) {
                return ResponseEntity.ok(Collections.singletonMap("token", token));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

}
