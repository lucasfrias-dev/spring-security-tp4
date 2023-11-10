package ar.edu.unnoba.poo.webappspring.dao;

import ar.edu.unnoba.poo.webappspring.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

// Clase UserDAO para el guardado de los usuarios en memoria.
public class UserDAO{
    // Lista de usuarios.
    public List<User> users;

    // Constructor de la clase UserDAO. Se inicializa con el usuario admin agregado a la lista de usuarios.
    public UserDAO(){
        users = new ArrayList<>();
        User usuario = new User();
        usuario.setId(1L);
        usuario.setApellido("admin");
        usuario.setNombre("admin");
        usuario.setEmail("admin");
        usuario.setPassword("1234");
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        users.add(usuario);
    }

    // Método para añadir un usuario a la lista de usuarios
    public void addUser(User user){
        users.add(user);
    }

    // Método para obtener la lista de usuarios
    public List<User> getUsers(){
        return users;
    }

    // Método para obtener un usuario por su correo electrónico
    public User getUserByEmail(String email){
        return this.getUsers().stream()
                .filter((User user) -> user.getEmail().equals(email)).findFirst().orElse(null);
    }
}

