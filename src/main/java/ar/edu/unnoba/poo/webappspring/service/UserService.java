package ar.edu.unnoba.poo.webappspring.service;

import ar.edu.unnoba.poo.webappspring.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

// Interfaz UserService que extiende de UserDetailsService de Spring Security
public interface UserService extends UserDetailsService {

    // Método para crear un usuario. Puede lanzar una excepción si ocurre algún error
    public void create(User user) throws Exception;
    // Método para obtener la lista de usuarios
    public List<User> getUsers();
    // Método para obtener un usuario por su correo electrónico
    public User getUserByEmail(String email);

}
