package com.cvds.eci.laboratoryreservations.app_core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserDetailService implements UserDetailsService {

    /**
     * Repository for retrieving user information from the database.
     */
    @Autowired
    private UserRepository userRepo;

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
        
        User user = userRepo.findByName(username);

       
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

       
        return new UsersDetails(user);
    }
}
