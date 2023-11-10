package ar.edu.unnoba.poo.webappspring.config;

import ar.edu.unnoba.poo.webappspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Inyección de dependencias de la clase UserService
    @Autowired
    UserService userService;

    // Método para configurar la cadena de filtros de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .userDetailsService(userService)
                .authorizeHttpRequests((requests) -> requests // Configura las solicitudes autorizadas
                        // Permite el acceso a ciertos recursos sin autenticación
                        .requestMatchers("/", "/webjars/**", "/resources/**","/css/**", "/js/**","/register", "/login").permitAll()
                        .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
                )
                // Configura el inicio de sesión basado en formularios
                .formLogin((form) -> form
                        .permitAll() // Permite el acceso a la página de inicio de sesión sin autenticación
                        // Configura la página de inicio de sesión y la página a la que se redirige después de un inicio de sesión exitoso
                        .loginPage("/login").permitAll().defaultSuccessUrl("/").failureUrl("/login?error=true")
                        // Configura los parámetros de nombre de usuario y contraseña
                        .usernameParameter("email").passwordParameter("password"))
                .logout((logout) -> logout // Configura el cierre de sesión
                        .permitAll() // Permite el acceso a la página de cierre de sesión sin autenticación
                        .logoutSuccessUrl("/login"))  // Configura la página a la que se redirige después de un cierre de sesión exitoso
                .build(); // Construye la cadena de filtros de seguridad
    }

    // Método para crear un codificador de contraseñas BCrypt
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
