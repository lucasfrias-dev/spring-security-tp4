package ar.edu.unnoba.poo.webappspring.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

// Clase User que implementa la interfaz UserDetails de Spring Security
public class User implements UserDetails {
    // Atributos de la clase User
    private Long id; // Identificador del usuario
    @NotBlank(message = "El nombre no puede estar vacío") // Validación de que el nombre no esté vacío al registrar un usuario
    private String nombre; // Nombre del usuario
    @NotBlank(message = "El apellido no puede estar vacío") // Validación de que el apellido no esté vacío al registrar un usuario
    private String apellido; // Apellido del usuario
    @Email(message = "El email debe ser valido") // Validación de que el email sea válido al registrar un usuario
    @NotBlank(message = "El email no puede estar vacío") // Validación de que el email no esté vacío al registrar un usuario
    private String email; // Email del usuario
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres") // Validación de que la contraseña tenga al menos 8 caracteres al registrar un usuario
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
            message = "La contraseña debe contener al menos una letra minúscula, una letra mayúscula y un número") // Validación de que la contraseña tenga al menos una letra minúscula, una letra mayúscula y un número al registrar un usuario
    @NotBlank(message = "La contraseña no puede estar vacía") // Validación de que la contraseña no esté vacía al registrar un usuario
    private String password; // Contraseña del usuario

    // Constructor de la clase User
    public User(){};

    //Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // Métodos de la interfaz UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Este método devuelve los roles del usuario. En este caso, todos los usuarios tienen el rol "ROLE_USER"
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        // Este método devuelve el nombre de usuario. En este caso, se utiliza el email como nombre de usuario
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Este método indica si la cuenta del usuario no ha expirado. En este caso, siempre devuelve true
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Este método indica si la cuenta del usuario no está bloqueada. En este caso, siempre devuelve true
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Este método indica si las credenciales del usuario no han expirado. En este caso, siempre devuelve true
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Este método indica si el usuario está habilitado. En este caso, siempre devuelve true
        return true;
    }

}
