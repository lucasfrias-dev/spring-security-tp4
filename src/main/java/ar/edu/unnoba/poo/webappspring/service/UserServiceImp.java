package ar.edu.unnoba.poo.webappspring.service;

import ar.edu.unnoba.poo.webappspring.dao.UserDAO;
import ar.edu.unnoba.poo.webappspring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{
    // Inyección de dependencias de la clase UserDAO
    @Autowired
    private UserDAO userDAO;

    // Implementación del método create de la interfaz UserService
    @Override
    public void create(User user) throws Exception{
        // Verifica si el email del usuario ya existe en la lista de usuarios
        if(!emailExists(user.getEmail())){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword())); // Encripta la contraseña del usuario
            user.setId(userDAO.getUsers().size() + 1L); // Establece el id del usuario
            userDAO.addUser(user); // Agrega el usuario a la lista de usuarios
        }
    }

    // Método privado para verificar si un email ya existe en la lista de usuarios
    private boolean emailExists(String email) throws Exception {
        User user = userDAO.getUserByEmail(email);
        // Si el usuario existe, lanza una excepción
        if (user != null){
            throw new Exception("El email ya está registrado"); // Lanza una excepción
        }
        // Si el usuario no existe, devuelve false
        return false;
    }

    // Implementación del método getUsers de la interfaz UserService
    @Override
    public List<User> getUsers() {
        // Devuelve la lista de usuarios
        return userDAO.getUsers();
    }

    // Implementación del método getUserByEmail de la interfaz UserService
    @Override
    public User getUserByEmail(String email) {
        // Devuelve el usuario por su email
        return userDAO.getUserByEmail(email);
    }

    // Implementación del método loadUserByUsername de la interfaz UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByEmail(username); // Obtiene el usuario por su email
        // Si el usuario no existe, lanza una excepción
        if (user == null){
            throw new UsernameNotFoundException("El usuario " + username + " no existe");
        }
        // Si el usuario existe, lo devuelve
        return user;
    }
}
