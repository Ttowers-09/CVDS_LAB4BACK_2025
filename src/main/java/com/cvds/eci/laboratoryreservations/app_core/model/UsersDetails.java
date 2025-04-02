package com.cvds.eci.laboratoryreservations.app_core.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Implementación de la interfaz {@link UserDetails} para la gestión de autenticación
 * y autorización de usuarios en Spring Security.
 */
public class UsersDetails implements UserDetails {

    /**
     * Instancia de {@link User} que representa al usuario autenticado.
     */
    private User user;

    /**
     * Constructor de la clase UsersDetails.
     *
     * @param user Objeto {@link User} que representa al usuario autenticado.
     */
    public UsersDetails(User user) {
        this.user = user;
    }

    /**
     * Obtiene los permisos o roles del usuario.
     * 
     * @return Una colección con la autoridad asignada al usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRol()));
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return Nombre del usuario.
     */
    @Override
    public String getUsername() {
        return user.getName();
    }

    /**
     * Indica si la cuenta del usuario ha expirado.
     *
     * @return `true` si la cuenta no ha expirado
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta del usuario está bloqueada.
     *
     * @return `true` si la cuenta no está bloqueada
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales del usuario han expirado.
     *
     * @return `true` si las credenciales no han expirado
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica si el usuario está habilitado.
     *
     * @return `true` si el usuario está habilitado
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Obtiene el objeto User asociado.
     *
     * @return user El objeto User.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el objeto User.
     *
     * @param user Nuevo objeto User a asignar.
     */
    public void setUser(User user) {
        this.user = user;
    }

    
}
