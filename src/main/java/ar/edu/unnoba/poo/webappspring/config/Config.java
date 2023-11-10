package ar.edu.unnoba.poo.webappspring.config;

import ar.edu.unnoba.poo.webappspring.dao.UserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public UserDAO getUserDAO(){
        return new UserDAO();
    }
}
