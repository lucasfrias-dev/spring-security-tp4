package ar.edu.unnoba.poo.webappspring.controller;

import ar.edu.unnoba.poo.webappspring.model.User;
import ar.edu.unnoba.poo.webappspring.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller // Anotación para indicar que esta clase es un controlador en Spring MVC
public class UserController {
    // Inyección de dependencias de la clase UserService
    @Autowired
    private UserService userService;

    // Método para manejar las solicitudes GET a la ruta "/login"
    @GetMapping("/login")
    public String Login(@RequestParam(value = "error", required = false) String error, Model model) {
        // Si hay un error, añade un mensaje de error al modelo
        if (error != null) {
            model.addAttribute("loginError", "Usuario o contraseña incorrectos");
        }
        // Añade un nuevo usuario al modelo
        model.addAttribute("user", new User());
        return "login"; // Retorna la vista "login"
    }

    // Método para manejar las solicitudes GET a la ruta "/register"
    @GetMapping("/register")
    public String register(Model model) {
        // Añade un nuevo usuario al modelo
        model.addAttribute("user", new User());
        // Retorna la vista "register"
        return "register";
    }

    // Método para manejar las solicitudes POST a la ruta "/register"
    @PostMapping("/register")
    public String createUser(@Valid @ModelAttribute("user")User user, BindingResult result, ModelMap model, @RequestParam("passwordConfirm") String passwordConfirm) {
        // Verifica si las contraseñas coinciden y si hay errores en el formulario de registro
        if (!user.getPassword().equals(passwordConfirm)) {
            result.rejectValue("password", "error.password", "Las contraseñas no coinciden");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/register";
        }

        // Intenta crear el usuario
        try {
            userService.create(user);
        } catch (Exception e) {  // Si ocurre un error, añade el mensaje de error al modelo y retorna la vista "register"
            model.addAttribute("formError", e.getMessage());
            model.addAttribute("user", user);
            return "/register";
        }
        // Si la creación del usuario fue exitosa, redirige a la ruta "/login"
        return "redirect:/login";
    }

    // Método para manejar las solicitudes GET a la ruta "/list"
    @GetMapping("/list")
    public String listUsers(Model model){
        // Añade la lista de usuarios al modelo
        model.addAttribute("users", userService.getUsers());
        return "list"; // Retorna la vista "list"
    }
}
