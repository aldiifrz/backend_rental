package com.example.backendrentalapp.controller;


import com.example.backendrentalapp.service.UsersService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.backendrentalapp.model.UsersModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(UsersController.class);

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@ModelAttribute UsersModel usersModel) {
        logger.info("Register request: {}", usersModel);

        UsersModel registeredUser = usersService.registerUsers(usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail());

        if (registeredUser != null) {
            return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/login").body("Registration successful");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during registration");
        }
    }
}
