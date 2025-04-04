package com.cvds.eci.laboratoryreservations.app_core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cvds.eci.laboratoryreservations.app_core.model.User;
import com.cvds.eci.laboratoryreservations.app_core.model.UsersDetails;
import com.cvds.eci.laboratoryreservations.app_core.repository.UserRepository;

/**
 * Service that implements the UserDetailsService interface to manage user authentication.
 * 
 * This class is responsible for retrieving user details from the database when a user
 * attempts to log in.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); 

    /**
     * Retrieves all users from the database.
     * 
     * @return A list of all registered users.
     * @throws RuntimeException If no users are found.
     */
    public List<User> getAllUsers() {
        List<User> list = userRepository.findAll();
        if (list.isEmpty()){
            throw new RuntimeException("The user's list is empty");
        }
        return list;
    }
    
    /**
     * Retrieves a user by their ID.
     * 
     * @param id The ID of the user.
     * @return The user associated with the given ID.
     * @throws RuntimeException If the user is not found.
     */
    public User getUserById(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            throw new RuntimeException("The user with the id: " + id + " cannot be found");
        }
        return user;
    }

    /**
     * Retrieves a user by their name.
     * 
     * @param name The name of the user.
     * @return The user associated with the given name.
     * @throws RuntimeException If the user is not found.
     */
    public User getUserByName(String name){
        User user = userRepository.findByName(name);
        if (user == null){
            throw new RuntimeException("The user " + name + " cannot be found" );
        }
        return user;
    }

    /**
     * Adds a new user to the database.
     * 
     * @param user The user object to be added.
     * @return The saved user object.
     * @throws RuntimeException If the user already exists.
     */
    public User addUser(User user) {
        User existingUser = userRepository.findByEmailAndName(user.getEmail(), user.getName());
        if (existingUser != null){
            throw new RuntimeException("The user " + user.getName() + " and mail " + user.getEmail() + " already exists .");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Deletes a user by ID.
     * 
     * @param id The ID of the user to be deleted.
     * @return The ID of the deleted user.
     * @throws RuntimeException If the user does not exist.
     */
    public String deleteUser(String id){
        User userSearch = userRepository.findById(id).orElse(null);
        if(userSearch == null){
            throw new RuntimeException("The user does not exist");
        }
        userRepository.deleteById(id);
        return id;
    }

    /**
     * Verifies user authentication and generates a JWT token if successful.
     * 
     * @param user The user attempting to log in.
     * @return A JWT token if authentication is successful, otherwise "fail".
     * @throws Exception 
     */
    public String verifyUserRole(User user, String requiredRole) throws Exception {
        UserDetails userDetails = loadUserByUsername(user.getName());
        Authentication authentication = 
            authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword(), userDetails.getAuthorities()));

        if (authentication.isAuthenticated()) {
            UsersDetails usersDetails = (UsersDetails) userDetails;
            String actualRole = usersDetails.getUser().getRol(); 
            if (!actualRole.equals(requiredRole)) {
                throw new Exception("Not authorized as " + requiredRole);
            }
            return jwtService.generateToken(user.getName(), actualRole);
        }

        throw new Exception("Authentication failed");
    }


    /**
     * Loads user details by username for authentication.
     * 
     * This method searches for a user in the database by username. If the user is found,
     * it returns a UserDetails object containing the user's authentication information.
     * If the user is not found, an exception is thrown.
     *
     * @param username The username of the user attempting to log in.
     * @return A UserDetails object containing the user's authentication information.
     * @throws UsernameNotFoundException If the user is not found in the database.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UsersDetails(user);
    }

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param userid Identificador del usuario a actualizar.
     * @param user Objeto User con los nuevos datos a modificar.
     * @throws RuntimeException Si el usuario con el ID proporcionado no existe.
     */
    public void updateUser(String userid,User user){
        Optional<User> userUpdate = userRepository.findById(userid);

        if (userUpdate.isPresent()) {
            User newUser = userUpdate.get();
            if (user.getName() != null) newUser.setName(user.getName());
            if (user.getEmail() != null) newUser.setEmail(user.getEmail());
            userRepository.save(newUser);
        } else {
            throw new RuntimeException("User with ID " + userid + " not found");
        }
    }

    /**
     * Verifica las credenciales de un usuario y genera un token de autenticación.
     *
     * @param user Objeto User con las credenciales ingresadas.
     * @return Token de autenticación si las credenciales son válidas.
     * @throws Exception Si el usuario no es encontrado o la contraseña es incorrecta.
     */
    public String verifyUserCredentials(User user) throws Exception {
        User userInDB = userRepository.findByName(user.getName());
        if (userInDB == null) {
            throw new Exception("Usuario no encontrado");
        }
    
        boolean passwordMatches = encoder.matches(user.getPassword(), userInDB.getPassword());
        if (!passwordMatches) {
            throw new Exception("Contraseña incorrecta");
        }
    
        // Genera el token usando el rol almacenado en BD
        return jwtService.generateToken(userInDB.getName(), userInDB.getRol());
    }
    
}
